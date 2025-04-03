package com.example.starter.eventbus.consumeroptions;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.MessageConsumerOptions;
import io.vertx.core.json.JsonObject;

public class RcvVerticle extends AbstractVerticle {

  @Override
  public void start()
  {
    System.out.println("RcvVerticle started on thread: " + Thread.currentThread().getName());

    EventBus eventBus = vertx.eventBus();

    MessageConsumerOptions options = new MessageConsumerOptions()
      .setAddress("news.uk.sport1")
      .setMaxBufferedMessages(10);

    MessageConsumer<Object> consumer = eventBus.consumer(options);

    consumer.handler(message -> {
      System.out.println("Received message on VerticleFive: " + message.body());
    });

    consumer.pause();

    vertx.setTimer(5000, id -> {
      consumer.resume();
      System.out.println("Consumer resumed after 5 seconds");
    });
  }

  @Override
  public void stop() {
    System.out.println("RcvVerticle stopped");
  }
}
