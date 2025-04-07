package com.example.starter.tcpserversandclients.sendfile;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class SendFileVericle extends AbstractVerticle {
  @Override
  public void start() {
    NetServerOptions options = new NetServerOptions().setPort(8080).setHost("localhost");

    NetServer server = vertx.createNetServer(options);

    server.connectHandler(socket -> {

      System.out.println("Client connected: " + socket.remoteAddress());

      socket.sendFile("/home/purvik/IdeaProjectsUltimate/starter/src/main/java/com/example/starter/hello1.txt");

      // Handle socket closure.
      socket.closeHandler(v -> {
        System.out.println("Client disconnected: " + socket.remoteAddress());
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

  @Override
  public void stop() {
    System.out.println("SendFileVericle stopped.");
  }
}
