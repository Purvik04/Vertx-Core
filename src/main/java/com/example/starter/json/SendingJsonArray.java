package com.example.starter.json;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class SendingJsonArray extends AbstractVerticle {
  @Override
  public void start()
  {
    // Create a JSON array
    JsonArray jsonArray = new JsonArray();

    User user = new User("Purvik", 25);

    // Add some JSON objects to the array
    jsonArray.add(new JsonObject().put("name", "Alice").put("age", 25));

    jsonArray.add(new JsonObject().put("name", "Bob").put("age", 30));

//    jsonArray.add(user); gives error when sending due to not serialization

    EventBus eventBus = vertx.eventBus();

    vertx.setTimer(1000, event -> {
      eventBus.send("json.array.address", jsonArray);
    });

  }
}
