package com.example.starter.eventbus.sendrecieve;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class SendRcvMain {
  public static void main(String[] args) throws InterruptedException {
    // Create a Vertx instance with custom options
    Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(5).setWorkerPoolSize(20));

    // Deploy the SenderVerticle
    vertx.deployVerticle(new SenderVerticle());

    // Deploy the ReceiverVerticle
    vertx.deployVerticle(new ReceiverVerticle());
  }
}
