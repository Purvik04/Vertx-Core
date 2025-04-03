package com.example.starter.testing;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;

public class TestVerticle3 extends AbstractVerticle {

  @Override
  public void start()
  {
    Context vertxContext = vertx.getOrCreateContext();

    vertxContext.put("name", "purvik3");

    System.out.println("MyVerticle3 started"+ Thread.currentThread().getName());
  }

  @Override
  public void stop()
  {
    System.out.println("MyVerticle stopped");
  }
}
