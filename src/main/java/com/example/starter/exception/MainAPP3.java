package com.example.starter.exception;

import io.vertx.core.Vertx;

public class MainAPP3 {
  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(FailVerticle.class.getName(), res -> {
      if (res.succeeded()) {
        System.out.println("Verticle deployed successfully!");
      } else {
        System.out.println("Failed to deploy verticle: " + res.cause().getMessage());
        res.cause().printStackTrace();
      }
    });
  }
}
