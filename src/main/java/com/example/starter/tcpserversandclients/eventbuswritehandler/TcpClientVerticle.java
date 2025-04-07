package com.example.starter.tcpserversandclients.eventbuswritehandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.buffer.Buffer;

public class TcpClientVerticle extends AbstractVerticle {

  @Override
  public void start() {
    NetClientOptions options = new NetClientOptions()
      .setConnectTimeout(10000); // 10 seconds connect timeout

    NetClient client = vertx.createNetClient(options);

    client.connect(8080, "localhost", res -> {
      if (res.succeeded()) {
        System.out.println("Connected to server.");
        var socket = res.result();

        // Send a message to the server
        String message = "Hello from Vert.x client!";
        socket.write(Buffer.buffer(message));

        // Handle incoming data from the server
        socket.handler(buffer -> {
          System.out.println("Received from server: " + buffer.toString());
        });

        // Handle socket closure
        socket.closeHandler(v -> {
          System.out.println("Connection closed by server.");
        });

        // Handle exceptions
        socket.exceptionHandler(e -> {
          System.out.println("Socket error: " + e.getMessage());
        });

      } else {
        System.out.println("Failed to connect to server: " + res.cause().getMessage());
      }
    });
  }
}

