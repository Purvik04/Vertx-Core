package com.example.starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyVerticle extends AbstractVerticle {

  @Override
  public void start()
  {
    System.out.println("MyVerticle started");

    Future<String> stringFuture = vertx.executeBlocking(() -> {

      try
      {
        Thread.sleep(15000);

        // config() is used to get the configuration passed as json object
//        System.out.println(config().getString("name"));
//
//        System.out.println(config().getInteger("age"));
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();

        System.out.println("Error: " + e.getMessage());
      }

      return "Hello from MyVerticle!";

    }, false);

    try
    {
      Thread.sleep(10000);
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    stringFuture.onComplete(result -> System.out.println("Result: " + result));
  }

  @Override
  public void stop()
  {
    System.out.println("MyVerticle stopped");
  }

}
