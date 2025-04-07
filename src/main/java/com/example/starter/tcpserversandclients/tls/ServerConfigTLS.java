package com.example.starter.tcpserversandclients.tls;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.PemKeyCertOptions;

public class ServerConfigTLS extends AbstractVerticle {
  @Override
  public void start() {
    NetServerOptions options = new NetServerOptions();
    options.setPort(8443)
           .setHost("localhost")
           .setSsl(true);

    options.setKeyCertOptions(new JksOptions().setPath("/home/purvik/IdeaProjectsUltimate/starter/keystore.jks")
      .setPassword("Mind@123"));

    NetServer server = vertx.createNetServer(options);

    server.connectHandler(socket -> {
          socket.handler(buffer -> {
            System.out.println("Received data: " + buffer.toString());
            socket.write("Hello from TLS server!");
          });
    });

    server.listen().onComplete(listen -> {
      if (listen.succeeded()) {
        System.out.println("TLS server started on port " + listen.result().actualPort());
      } else {
        System.out.println("Failed to start TLS server: " + listen.cause());
      }
    });
  }

  @Override
  public void stop() {
    System.out.println("TLS Server stopped.");
  }

  public static void main(String[] args) throws InterruptedException {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(ServerConfigTLS.class.getName());

    Thread.sleep(1000);

    vertx.deployVerticle(TcpClientTls.class.getName());
  }
}
