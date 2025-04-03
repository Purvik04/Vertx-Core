package com.example.starter.eventbus.typesofmessages;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

class SenderVerticle extends AbstractVerticle
{
  @Override
  public void start()
  {
    // Delay a bit to ensure the receiver is ready.
    vertx.setTimer(1000, id -> {

      // Send a primitive integer.
      vertx.eventBus().send("example.address", 42);

      // Send a String.
      vertx.eventBus().send("example.address", "Hello Vert.x!");

      // Send a JSON object.
      JsonObject json = new JsonObject().put("message", "This is a JSON message");

      vertx.eventBus().send("example.address", json);

      // Send a Buffer.
      Buffer buffer = Buffer.buffer("This is a Buffer message");

      vertx.eventBus().send("example.address", buffer);

      // Send a custom object (Person). This requires the custom codec.
      Person person = new Person("Alice", 30);

      vertx.eventBus().send("example.address", person);
    });
  }
}

