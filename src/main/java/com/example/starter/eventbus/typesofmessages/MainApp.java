package com.example.starter.eventbus.typesofmessages;

import io.vertx.core.Vertx;

// Main application to deploy both verticles and register the custom codec.

public class MainApp
{
  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx();

    // Register the custom codec for Person objects.
    vertx.eventBus().registerDefaultCodec(Person.class, new PersonCodec());

    vertx.deployVerticle(new RecieverVerticle());

    vertx.deployVerticle(new SenderVerticle());
  }
}
