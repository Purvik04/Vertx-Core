package com.example.starter.eventbus.typesofmessages;

import io.vertx.core.AbstractVerticle;

// ReceiverVerticle listens on "example.address" and prints each message.

class RecieverVerticle extends AbstractVerticle
{
  @Override
  public void start()
  {
    vertx.eventBus().consumer("example.address", message -> {

      Object body = message.body();

      System.out.println("Received message: " + body + " (Type: " + body.getClass().getName() + ")");
    });
  }
}
