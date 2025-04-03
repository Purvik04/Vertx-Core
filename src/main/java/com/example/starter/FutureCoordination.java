package com.example.starter;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class FutureCoordination {

  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx();

    Future<String> stringFuture1 = vertx.executeBlocking(() -> {
      try
      {
        Thread.sleep(10000);

        throw new RuntimeException();
      }
      catch (InterruptedException e)
      {
        System.out.println("Error: " + e.getMessage());
      }

      return "Task 1 completed";
    }, false);

    Future<String> stringFuture2 = vertx.executeBlocking(() -> {
      try
      {
        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        System.out.println("Error: " + e.getMessage());
      }

      return "Task 2 completed";
    }, false);

    Future.all(stringFuture1, stringFuture2).onComplete(res -> {
      if (res.succeeded())
      {
        System.out.println("All tasks completed successfully from all");

        System.out.println("Result 1: " + stringFuture1.result());

        System.out.println("Result 2: " + stringFuture2.result());
      }
      else
      {
        System.out.println("One or more tasks failed from all: " + res.cause());
      }
    });

    Future.any(stringFuture1, stringFuture2).onComplete(res -> {
      if (res.succeeded())
      {
        System.out.println("At least one task completed successfully from any");

        System.out.println("Result: " + res.result());
      }
      else
      {
        System.out.println("All tasks failed: " + res.cause());
      }
    });

    Future.join(stringFuture1, stringFuture2).onComplete(res -> {
      if (res.succeeded())
      {
        System.out.println("All tasks completed successfully from join");

        System.out.println("Result 1: " + stringFuture1.result());

        System.out.println("Result 2: " + stringFuture2.result());
      }
      else
      {
        System.out.println("One or more tasks failed from join: " + res.cause());
      }
    });


  }
}
