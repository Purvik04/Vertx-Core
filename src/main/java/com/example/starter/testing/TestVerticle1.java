package com.example.starter.testing;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;

public class TestVerticle1 extends AbstractVerticle {

  @Override
  public void start()
  {
    Context vertxContext = vertx.getOrCreateContext();

    vertxContext.put("name", "purvik1");

    System.out.println("MyVerticle1 started"+ Thread.currentThread().getName());
  }

  @Override
  public void stop()
  {
    System.out.println("MyVerticle stopped");
  }
}
