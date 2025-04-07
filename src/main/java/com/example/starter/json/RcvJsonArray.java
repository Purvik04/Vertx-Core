package com.example.starter.json;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;

public class RcvJsonArray extends AbstractVerticle {

  @Override
  public void start()
  {
    vertx.eventBus().consumer("json.array.address", message -> {

      JsonArray jsonArray = (JsonArray) message.body();

      System.out.println("Received JSON Array: " + jsonArray.encodePrettily());
    });
  }
}
