package com.example.starter.eventbus.sendrr;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.MessageConsumerOptions;

public class VerticleFive extends AbstractVerticle {

  @Override
  public void start()
  {
    System.out.println("VerticleFive started on thread: " + Thread.currentThread().getName());

    EventBus eventBus = vertx.eventBus();

    MessageConsumerOptions options = new MessageConsumerOptions()
      .setAddress("news.uk.sport1")
      .setMaxBufferedMessages(2000);

    MessageConsumer<Object> consumer = eventBus.consumer(options);

    consumer.handler(message -> {
      System.out.println("Received message on VerticleFive: " + message.body());
//      System.out.println("Headers: " + message.headers());
    });

  }

  @Override
  public void stop()
  {
    System.out.println("VerticleOne stopped");
  }
}

