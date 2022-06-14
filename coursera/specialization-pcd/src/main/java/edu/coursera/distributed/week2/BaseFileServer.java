package edu.coursera.distributed.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A basic and very limited implementation of a file server that responds to GET requests from HTTP clients.
 */
public class BaseFileServer {

    protected final int nCores;
    protected final PCDPFilesystem fileSystem;

    public BaseFileServer(int nCores, PCDPFilesystem fileSystem) {
        this.nCores = nCores;
        this.fileSystem = fileSystem;
    }

    public BaseFileServer(PCDPFilesystem fileSystem) {
        this.fileSystem = fileSystem;
        this.nCores = 1;
    }

    public int getnCores() {
        return nCores;
    }

    public PCDPFilesystem getFileSystem() {
        return fileSystem;
    }

    /**
     * Main entrypoint for the basic file server.
     *
     * @param socket Provided socket to accept connections on.
     * @throws IOException If an I/O error is detected on the server. This should be a fatal error, your file server
     *                     implementation is not expected to ever throw IOExceptions during normal operation.
     */
    public void run(final ServerSocket socket) throws IOException {
        /*
         * Enter a spin loop for handling client requests to the provided ServerSocket object.
         */
        while (true) {
            // TODO 1) Use socket.accept to get a Socket object
            Socket s = socket.accept();
            accept(s);
        }

    }

    protected void accept(Socket s) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter writer = new PrintWriter(s.getOutputStream())
        ) {
            String line = reader.readLine();
            assert line != null;
            assert line.startsWith("GET");
            /*
             * TODO 2) Now that we have a new Socket object, handle the parsing of the HTTP message on that socket and returning of the requested
             * file in a separate thread. You are free to choose how that new thread is created. Common approaches would include spawning a new
             * Java Thread or using a Java Thread Pool. The steps to complete the handling of HTTP messages are the same as in MiniProject 2,
             * but are repeated below for convenience:
             *
             *   a) Using Socket.getInputStream(), parse the received HTTP packet. In particular, we are interested in confirming this
             *      message is a GET and parsing out the path to the file we are GETing. Recall that for GET HTTP packets, the first line of
             *      the received packet will look something like:
             *
             *          GET /path/to/file HTTP/1.1
             *   b) Using the parsed path to the target file, construct an HTTP reply and write it to Socket.getOutputStream(). If the
             *      file exists, the HTTP reply should be formatted as follows:
             *
             *        HTTP/1.0 200 OK\r\n
             *        Server: FileServer\r\n
             *        \r\n
             *        FILE CONTENTS HERE\r\n
             *
             *      If the specified file does not exist, you should return a reply with an error code 404 Not Found. This reply should be
             *      formatted as:
             *
             *        HTTP/1.0 404 Not Found\r\n
             *        Server: FileServer\r\n
             *        \r\n
             *
             */
            final String filePath = line.split(" ")[1];
            PCDPPath path = new PCDPPath(filePath);
            String content = fileSystem.readFile(path);
            if (content != null) {
                writer.write("HTTP/1.0 200 OK\r\n");
                writer.write("\r\n");
                writer.write("\r\n");
                writer.write(content);
            } else {
                writer.write("HTTP/1.0 404 Not Found\r\n");
                writer.write("\r\n");
                writer.write("\r\n");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
