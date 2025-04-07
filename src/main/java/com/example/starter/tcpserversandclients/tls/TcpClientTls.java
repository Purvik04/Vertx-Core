package com.example.starter.tcpserversandclients.tls;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;

public class TcpClientTls extends AbstractVerticle {
  @Override
  public void start() {

    NetClientOptions options = new NetClientOptions();
    options.setSsl(true).setTrustAll(true).setHostnameVerificationAlgorithm("");

    NetClient netClient = vertx.createNetClient(options);

    netClient.connect(8443, "localhost", result -> {
      if (result.succeeded()) {
        System.out.println("Connected to TLS server!");

        var socket = result.result();

        socket.write("Hello from TLS client!");

        socket.handler(buffer -> {
          System.out.println("Received data: " + buffer.toString());
        });
      } else {
        System.out.println("Failed to connect to TLS server: " + result.cause());
      }
    });
  }
}
