package edu.coursera.distributed.week2;

import edu.coursera.Util;

import java.io.IOException;

public class MultithreadedFileServerTest extends BaseFileServerTest {

    static {
        files.put("/static/large.txt", getRandomFileContents(1048576));

        baseServer = new MultithreadedFileServer(Util.getNCores(), getFilesystem(files));
    }

    private int runPerformanceTest(BaseFileServer baseServer) throws IOException, InterruptedException {
        final HttpServer server = new HttpServer(baseServer);

        final int nDriverThreads = baseServer.getnCores();
        Thread[] driverThreads = new Thread[nDriverThreads];
        RequestDriver[] drivers = new RequestDriver[driverThreads.length];

        for (int i = 0; i < drivers.length; i++) {
            drivers[i] = new RequestDriver(server);
            drivers[i].addRequest(rootDirName + "/large.txt", files.get("/static/large.txt"));
            driverThreads[i] = new Thread(drivers[i]);
        }

        for (Thread driverThread : driverThreads) {
            driverThread.start();
        }

        Thread.sleep(10000);

        server.terminate();

        int completedRequests = 0;
        for (RequestDriver driver : drivers) {
            completedRequests += driver.getNRequests();
        }
        return completedRequests;
    }

    public void testPerformance() throws IOException, InterruptedException {
        final int nDriverThreads = baseServer.getnCores();
        System.err.println("Testing performance of multi-threaded web server using " + nDriverThreads + " request threads");
        final int seqRequests = runPerformanceTest(new BaseFileServer(baseServer.getFileSystem()));
        final int parallelRequests = runPerformanceTest(baseServer);

        final double improvement = (double) parallelRequests / (double) seqRequests;

        System.err.println("Single-core execution completed " + seqRequests);
        System.err.println("Parallel execution completed " + parallelRequests + ", yielding an improvement of " +
                improvement + "x");
        System.err.println();

        /*
         * Expect some parallel improvement, though we don't expect it to scale perfectly with threads.
         */
        final double expected;
        if (nDriverThreads == 2) {
            expected = 1.0;
        } else if (nDriverThreads == 4) {
            expected = 2.0;
        } else {
            expected = 0.6 * nDriverThreads;
        }
        final String msg = "Expected parallel threads to produce at least a " + expected +
                "x improvement, but only saw " + improvement + "x";
        assertTrue(msg, improvement > expected);
    }
}
