package edu.coursera.distributed.week2;

import edu.coursera.distributed.week2.HttpResponse;
import edu.coursera.distributed.week2.HttpServer;
import junit.framework.TestCase;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class RequestDriver implements Runnable {
    private final Map<String, String> requests = new HashMap<String, String>();
    private final HttpServer server;
    private int nRequests = 0;

    public RequestDriver(HttpServer server) {
        this.server = server;
    }

    public void addRequest(final String filename, final String body) {
        requests.put(filename, body);
    }

    public int getNRequests() {
        return nRequests;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Map.Entry<String, String> r : requests.entrySet()) {
                    HttpResponse response = server.sendHttpRequest(r.getKey(), false);
                    TestCase.assertEquals(response.body, r.getValue());
                }

                nRequests++;
            }
        } catch (ConnectException c) {
            // Is fine, server shut down
        } catch (SocketException s) {
            // Is fine, server shut down
        } catch (IOException io) {
            throw new RuntimeException(io);
        }
    }
}