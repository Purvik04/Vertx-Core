package com.example.starter.json;

import io.vertx.core.Vertx;

public class JsonMainApp {
  public static void main(String[] args) {
    // Create a Vert.x instance
    Vertx vertx = Vertx.vertx();

    // Deploy the SendingJsonArray verticle
    vertx.deployVerticle(new SendingJsonArray());

    // Deploy the RcvJsonArray verticle
    vertx.deployVerticle(new RcvJsonArray());
  }
}
