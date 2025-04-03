package com.example.starter.eventbus.sendrr;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class EventBusMain {
  public static void main(String[] args) throws InterruptedException {

    Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(5).setWorkerPoolSize(20));

    vertx.deployVerticle(new VerticleOne());

    vertx.deployVerticle(new Verticletwo());

    vertx.deployVerticle(new VerticleThree());

    vertx.deployVerticle(new VerticleFour());

    vertx.deployVerticle(new VerticleFive());

  }
}
