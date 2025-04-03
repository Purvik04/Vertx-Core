package com.example.starter.eventbus.sendrr;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

public class VerticleOne extends AbstractVerticle {

  @Override
  public void start() throws InterruptedException {

    System.out.println("VerticleOne started on thread: " + Thread.currentThread().getName());

    EventBus eventBus = vertx.eventBus();

    Future<Object> objectFuture = vertx.executeBlocking(() -> {

      int i = 1 ;

      DeliveryOptions options = new DeliveryOptions()
        .addHeader("name","purvik")
        .addHeader("age","28")
        .setSendTimeout(5000);

      while (true)
      {
        eventBus.publish("news.uk.sport1", "Hello from VerticleOne!" + i, options);

        i++;

        Thread.sleep(1000);
      }
    }, false);

  }

  @Override
  public void stop()
  {
    System.out.println("VerticleOne stopped");
  }

}
