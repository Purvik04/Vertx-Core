package com.example.starter.tcpserversandclients.eventbuswritehandler;

import io.vertx.core.Vertx;

public class MainApp4 {
  public static void main(String[] args) throws InterruptedException {
    Vertx vertx = Vertx.vertx();

    // Deploy the TCP server verticle.
    vertx.deployVerticle(new TcpServerVerticle(), res -> {
      if (res.succeeded()) {
        System.out.println("TcpServerVerticle deployed successfully.");
      } else {
        System.out.println("Failed to deploy TcpServerVerticle: " + res.cause());
      }
    });

    Thread.sleep(1000);
    // Deploy the message sender verticle.
    vertx.deployVerticle(new MessageSenderVerticle(), res -> {
      if (res.succeeded()) {
        System.out.println("MessageSenderVerticle deployed successfully.");
      } else {
        System.out.println("Failed to deploy MessageSenderVerticle: " + res.cause());
      }
    });

    vertx.deployVerticle(new TcpClientVerticle(), res -> {
      if (res.succeeded()) {
        System.out.println("TcpClientVerticle deployed successfully.");
      } else {
        System.out.println("Failed to deploy TcpClientVerticle: " + res.cause());
      }
    });
  }
}
