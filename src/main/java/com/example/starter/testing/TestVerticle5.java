package com.example.starter.testing;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;

public class TestVerticle5 extends AbstractVerticle {

  @Override
  public void start() {

    Context vertxContext = vertx.getOrCreateContext();

    System.out.println("Context name: " + vertxContext.get("name"));

    System.out.println("MyVerticle5 started" + Thread.currentThread().getName());
  }

  @Override
  public void stop()
  {
    System.out.println("MyVerticle stopped");
  }
}
