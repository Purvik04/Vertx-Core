package com.example.starter.tcpserversandclients;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class sampleTcpServer {
  /**
   * This is a simple TCP server that listens on port 8080 and accepts incoming connections.
   * It reads data from the client, prints it to the console, and sends a response back to the client.
   */
  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx();

    NetServerOptions options = new NetServerOptions()
      .setPort(8080)
      .setHost("localhost")
      .setAcceptBacklog(5);

    NetServer netServer = vertx.createNetServer(options);

    netServer.connectHandler(socket->{

      socket.handler(buff->{

        System.out.println("Received data: " + buff.toString());

        try
        {
          // Correctly format the response in UTF format
          ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

          DataOutputStream dataOut = new DataOutputStream(byteStream);

          dataOut.writeUTF("Hello from Server");

          dataOut.flush();

          // Send properly formatted UTF data
          socket.write(Buffer.buffer(byteStream.toByteArray()));

        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      });

      socket.closeHandler(v -> System.out.println("The socket has been closed with client: " + socket.remoteAddress()));

      socket.exceptionHandler(exception->{
        System.out.println("Exception occurred: " + exception.getMessage());
      });
    });



    netServer.listen()
      .onComplete(result -> {
        if (result.succeeded())
        {
          System.out.println("Server is listening on port " + netServer.actualPort());
        }
        else
        {
          System.out.println("Failed to start server: " + result.cause());
        }
    });


  }
}
