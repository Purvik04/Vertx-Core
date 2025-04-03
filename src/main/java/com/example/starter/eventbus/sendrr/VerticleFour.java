package com.example.starter.eventbus.sendrr;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

public class VerticleFour extends AbstractVerticle {

  @Override
  public void start()
  {
    System.out.println("VerticleFour started on thread: " + Thread.currentThread().getName());

    EventBus eventBus = vertx.eventBus();

    MessageConsumer<String> consumer = eventBus.consumer("news.uk.sport1", message -> {
      System.out.println("Received message on VerticleFour: " + message.body());
    });

  }

  @Override
  public void stop()
  {
    System.out.println("VerticleOne stopped");
  }
}

