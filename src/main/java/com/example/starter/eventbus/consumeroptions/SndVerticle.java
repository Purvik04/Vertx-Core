package com.example.starter.eventbus.consumeroptions;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class SndVerticle extends AbstractVerticle {
  @Override
  public void start()
  {
    System.out.println("SndVerticle started on thread: " + Thread.currentThread().getName());

    EventBus eventBus = vertx.eventBus();

    vertx.setTimer(1000, id ->{

      for (int i = 1; i<=20; i++)
      {
        eventBus.send("news.uk.sport1", "Hello from SndVerticle" + i);
      }
    });
  }

  @Override
  public void stop() {
    System.out.println("SndVerticle stopped");
  }
}
