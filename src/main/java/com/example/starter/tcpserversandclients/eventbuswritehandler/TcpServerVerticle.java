package com.example.starter.tcpserversandclients.eventbuswritehandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TcpServerVerticle extends AbstractVerticle {

  // Shared list to hold the write handler IDs for all connected sockets.
  public static final List<String> writeHandlerIDs = new CopyOnWriteArrayList<>();

  @Override
  public void start() {
    NetServerOptions options = new NetServerOptions()
      .setPort(8080)
      .setHost("localhost")
      .setRegisterWriteHandler(true)
      .setAcceptBacklog(5);

    NetServer server = vertx.createNetServer(options);

    server.connectHandler(socket -> {
      System.out.println("Client connected: " + socket.remoteAddress());

      // Enable event bus write handler on this socket.
      String handlerID = socket.writeHandlerID();
      System.out.println("Registered write handler: " + handlerID);

      // Add the handler ID to the shared list.
      writeHandlerIDs.add(handlerID);

      // Handle incoming data from the client.
      socket.handler(buffer -> {
        System.out.println("Received from client (" + socket.remoteAddress() + "): " + buffer.toString());
      });

      // Handle socket closure.
      socket.closeHandler(v -> {
        System.out.println("Client disconnected: " + socket.remoteAddress());
        // Remove the handler ID when the socket is closed.
        writeHandlerIDs.remove(handlerID);
      });

      // Handle socket exceptions.
      socket.exceptionHandler(err -> {
        System.out.println("Socket error (" + socket.remoteAddress() + "): " + err.getMessage());
      });
    });

    server.listen(ar -> {
      if (ar.succeeded()) {
        System.out.println("TCP server is listening on port " + server.actualPort());
      } else {
        System.out.println("Failed to start TCP server: " + ar.cause());
      }
    });
  }
}

