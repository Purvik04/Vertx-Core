package com.example.starter.testing;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;

public class TestVerticle2 extends AbstractVerticle {

  @Override
  public void start()
  {
    Context vertxContext = vertx.getOrCreateContext();

    vertxContext.put("name", "purvik");

    System.out.println("MyVerticle2 started" + Thread.currentThread().getName());
  }

  @Override
  public void stop()
  {
    System.out.println("MyVerticle stopped");
  }
}
