package com.example.starter.verticles;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.concurrent.atomic.AtomicReference;

public class VerticleMain {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx(new VertxOptions()
                                  .setEventLoopPoolSize(10)
                                  .setWorkerPoolSize(20)
                                  .setInternalBlockingPoolSize(30));

    final String[] verticleID = new String[1];

    vertx.deployVerticle(new AsyncStartStop(), res -> {

      if (res.succeeded())
      {
        System.out.println("MyVerticle deployed successfully");

        verticleID[0] = res.result();
      }
      else
      {
        System.out.println("Failed to deploy MyVerticle: " + res.cause());
      }
    });

    vertx.undeploy(verticleID[0]);
  }
}
