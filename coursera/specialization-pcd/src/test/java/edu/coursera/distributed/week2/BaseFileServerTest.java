package edu.coursera.distributed.week2;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BaseFileServerTest extends TestCase {
    protected static final String rootDirName = "static";
    protected static final Map<String, String> files = new HashMap<String, String>();
    private static final File rootDir = new File(rootDirName);
    private static final Random rand = new Random();
    protected static BaseFileServer baseServer = null;

    static {
        initFilesMap();

        baseServer = new BaseFileServer(getFilesystem(files));
    }

    protected static void initFilesMap() {
        files.put("/static/A.txt", getRandomFileContents(5));
        files.put("/static/B.txt", getRandomFileContents(10));
        files.put("/static/dir1/C.txt", getRandomFileContents(10));
        files.put("/static/dir3/dir4/E.txt", getRandomFileContents(10));
        files.put("/static/ABC.txt", getRandomFileContents(2048));
    }

    protected static String getRandomFileContents(final int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // TODO add as after all
    public static void deleteRecursively(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                deleteRecursively(c);
            }
        }

        if (f.exists()) {
            if (!f.delete()) {
                throw new FileNotFoundException("Failed to delete file: " + f);
            }
        }
    }

    protected static PCDPFilesystem getFilesystem(Map<String, String> files) {
        PCDPFilesystem fs = new PCDPFilesystem();

        for (Map.Entry<String, String> entry : files.entrySet()) {
            PCDPPath path = new PCDPPath(entry.getKey());
            fs.addFile(path, entry.getValue());
        }

        return fs;
    }

    public void testTermination() throws IOException, InterruptedException {
        final HttpServer server = new HttpServer(baseServer);

        server.terminate();
    }

    public void testFileGet() throws IOException, InterruptedException {
        final HttpServer server = new HttpServer(baseServer);

        HttpResponse response = server.sendHttpRequest(rootDirName + "/A.txt", true);
        assertEquals(200, response.code);
        assertEquals(files.get("/static/A.txt"), response.body);

        server.terminate();
    }

    public void testFileGets() throws IOException, InterruptedException {

        final HttpServer server = new HttpServer(baseServer);

        HttpResponse response = server.sendHttpRequest(rootDirName + "/A.txt", true);
        assertEquals(200, response.code);
        assertEquals(files.get("/static/A.txt"), response.body);

        response = server.sendHttpRequest(rootDirName + "/B.txt", true);
        assertEquals(200, response.code);
        assertEquals(files.get("/static/B.txt"), response.body);

        server.terminate();
    }

    public void testNestedFileGet() throws IOException, InterruptedException {

        final HttpServer server = new HttpServer(baseServer);

        HttpResponse response = server.sendHttpRequest(rootDirName + "/dir1/C.txt", true);
        assertEquals(200, response.code);
        assertEquals(files.get("/static/dir1/C.txt"), response.body);

        server.terminate();
    }

    public void testDoublyNestedFileGet() throws IOException, InterruptedException {

        final HttpServer server = new HttpServer(baseServer);

        HttpResponse response = server.sendHttpRequest(rootDirName + "/dir3/dir4/E.txt", true);
        assertEquals(200, response.code);
        assertEquals(files.get("/static/dir3/dir4/E.txt"), response.body);

        server.terminate();
    }

    public void testLargeFileGet() throws IOException, InterruptedException {

        final HttpServer server = new HttpServer(baseServer);

        HttpResponse response = server.sendHttpRequest(rootDirName + "/ABC.txt", true);
        assertEquals(200, response.code);
        assertEquals(files.get("/static/ABC.txt"), response.body);

        server.terminate();
    }

    public void testMissingFileGet() throws IOException, InterruptedException {

        final HttpServer server = new HttpServer(baseServer);

        HttpResponse response = server.sendHttpRequest(rootDirName + "/missing.txt", true);
        assertEquals(404, response.code);

        server.terminate();
    }

    public void testMissingNestedFileGet() throws IOException, InterruptedException {
        final HttpServer server = new HttpServer(baseServer);

        HttpResponse response = server.sendHttpRequest(rootDirName + "/missingdir/missing.txt", true);
        assertEquals(404, response.code);

        server.terminate();
    }
}
