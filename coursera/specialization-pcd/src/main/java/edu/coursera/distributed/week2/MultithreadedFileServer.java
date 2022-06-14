package edu.coursera.distributed.week2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A basic and very limited implementation of a file server that responds to GET
 * requests from HTTP clients.
 */
public final class MultithreadedFileServer extends BaseFileServer {
    public MultithreadedFileServer(int nCores, PCDPFilesystem fileSystem) {
        super(nCores, fileSystem);
    }

    /**
     * Main entrypoint for the basic file server.
     *
     * @param socket Provided socket to accept connections on.
     * @throws IOException If an I/O error is detected on the server. This
     *                     should be a fatal error, your file server
     *                     implementation is not expected to ever throw
     *                     IOExceptions during normal operation.
     */
    public void run(final ServerSocket socket) throws IOException {
        /*
         * Enter a spin loop for handling client requests to the provided ServerSocket object.
         */
        ExecutorService executorService = Executors.newFixedThreadPool(nCores);
        while (true) {

            Socket s = socket.accept();
            Runnable thread = () -> {
                accept(s);
            };
            executorService.submit(thread);
        }
    }
}
