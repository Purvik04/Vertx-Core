package com.example.starter;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class FutureCoordination {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    Future<String> stringFuture1 = vertx.executeBlocking(() -> {
      try {
        Thread.sleep(10000);

      } catch (InterruptedException e) {
      }

      return "Task 1 completed";
    }, false);

    Future<String> stringFuture2 = vertx.executeBlocking(() -> {
      try {
        Thread.sleep(10000);

      } catch (InterruptedException e) {
      }

      return "Task 2 completed";
    }, false);

    Future.all(stringFuture1, stringFuture2).onComplete(res -> {
      if (res.succeeded()) {
        System.out.println("All tasks completed successfully");
        System.out.println("Result 1: " + stringFuture1.result());
        System.out.println("Result 2: " + stringFuture2.result());
      } else {
        System.out.println("One or more tasks failed: " + res.cause());
      }
    });


  }
}
