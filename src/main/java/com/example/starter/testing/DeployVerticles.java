package com.example.starter.testing;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class DeployVerticles {
  public static void main(String[] args) throws InterruptedException {
    // Deploying verticles
    Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(4));

    vertx.deployVerticle(new TestVerticle1());
    Thread.sleep(1000);
    vertx.deployVerticle(new TestVerticle2());
    Thread.sleep(1000);
    vertx.deployVerticle(new TestVerticle3());
    Thread.sleep(1000);
    vertx.deployVerticle(new TestVerticle4());
    Thread.sleep(1000);
    vertx.deployVerticle(new TestVerticle5());

  }
}
