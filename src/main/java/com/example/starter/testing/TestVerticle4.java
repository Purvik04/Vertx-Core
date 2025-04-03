package com.example.starter.testing;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;

public class TestVerticle4 extends AbstractVerticle {

  @Override
  public void start()
  {
    Context vertxContext = vertx.getOrCreateContext();

//    vertxContext.put("name", "purvik4");

    System.out.println("Context name: " + vertxContext.get("name"));

    System.out.println("MyVerticle4 started" + Thread.currentThread().getName());
  }

  @Override
  public void stop()
  {
    System.out.println("MyVerticle stopped");
  }
}
