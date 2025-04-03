package com.example.starter.eventbus.sendrecieve;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

// ReceiverVerticle: Listens on "infinite.address" and replies to incoming messages.

public class ReceiverVerticle extends AbstractVerticle
{
  @Override
  public void start()
  {
    vertx.eventBus().consumer("infinite.address", message -> {

      System.out.println("Receiver got: " + message.body());

      try {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }

      message.reply("Hii");
    });
  }
}
