package edu.coursera.distributed.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.URL;
import java.nio.channels.ClosedByInterruptException;
import java.util.concurrent.ThreadLocalRandom;

public class HttpServer {
  private final Thread thread;
  private final ServerSocket socket;
  private final int port;

  public HttpServer(BaseFileServer server) throws IOException {
    System.err.println(
        "\nLaunching server for " + Thread.currentThread().getStackTrace()[2].getMethodName());
    port = ThreadLocalRandom.current().nextInt(3000, 9000);

    socket = new ServerSocket(port);
    socket.setReuseAddress(true);

    Runnable runner =
        () -> {
          try {
            server.run(socket);
          } catch (SocketException s) {
            // Do nothing, assume killed by main thread
          } catch (ClosedByInterruptException s) {
            // Do nothing, assume killed by main thread
          } catch (IOException io) {
            throw new RuntimeException(io);
          }
        };

    thread = new Thread(runner);
    thread.start();
  }

  public HttpResponse sendHttpRequest(final String path, final boolean print) throws IOException {
    assert !path.startsWith("/");

    if (print) {
      System.err.print("Requesting " + path + "... ");
    }

    URL obj = new URL("http://localhost:" + port + "/" + path);

    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    con.setConnectTimeout(5000); // 5 seconds
    con.setReadTimeout(5000); // 5 seconds

    final int responseCode = con.getResponseCode();

    final String responseStr;
    if (responseCode != 404) {
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      responseStr = response.toString();
    } else {
      responseStr = "";
    }

    if (print) {
      System.err.println(
          "Response code is " + responseCode + ", with content length " + responseStr.length());
    }

    return new HttpResponse(responseCode, responseStr);
  }

  public void terminate() throws IOException, InterruptedException {
    System.err.println(
        "Stopping server for " + Thread.currentThread().getStackTrace()[2].getMethodName());
    socket.close();
    thread.interrupt();
    thread.join();
  }
}
