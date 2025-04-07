package com.example.starter.json;

import io.vertx.core.Vertx;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class JsonDemo {

  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx();

    /*
    A JSON object is basically just a map which has string keys and values can be of one of the
    JSON supported types (string, number, boolean).
    */

    String jsonString = "{\"foo\":\"bar\"}";

    JsonObject object = new JsonObject(jsonString);

    Map<String, Object> map = new HashMap<>();
    map.put("foo", "bar");
    map.put("xyz", 3);

    JsonObject object2 = new JsonObject(map);

    JsonObject object3 = new JsonObject();
    object3.put("foo", "bar").put("num", 123).put("mybool", true);

    User user = new User("Alice", 25);

    // Convert Java object to JSON
    JsonObject jsonObject = JsonObject.mapFrom(user);

    // Print JSON
    System.out.println("Converted JSON: " + jsonObject.encodePrettily());

    // Create a JSON object
    JsonObject jsonObject2 = new JsonObject()
      .put("name", "Alice")
      .put("age", 25);

    // Convert JSON to Java object
    User user2 = jsonObject.mapTo(User.class);

    // Print Java object fields
    System.out.println("Converted Java Object: " + user2.getName() + ", " + user2.getAge());

    JsonArray jsonArray = new JsonArray();
    jsonArray.add(user);
    jsonArray.add(jsonObject);
    jsonArray.add(jsonObject2);

    System.out.println(jsonArray);


//    String arbitraryJson = "[1, 2, 3]"; -> Valid JSON Array

//    String arbitraryJson = "{\"foo\":\"bar\"}"; -> Valid JSON Object

    String arbitraryJson = "{ name: 'Alice', age: 25, }";  // Invalid JSON

    try
    {
      Object object5 = Json.decodeValue(arbitraryJson);

      if (object5 instanceof JsonObject)
      {
        System.out.println("Valid JSON Object: " + object5);
      }
      else if (object5 instanceof JsonArray)
      {
        System.out.println("Valid JSON Array: " + object5);
      }
    }
    catch (DecodeException e)
    {
      System.out.println("❌ Invalid JSON: " + e.getMessage());
    }

  }
}

