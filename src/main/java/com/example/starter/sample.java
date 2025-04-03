package com.example.starter;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class sample {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(10).setWorkerPoolSize(20));

    vertx.setTimer(5000, id -> {
      System.out.println("Hello from Vert.x setTimer!");
    });

    long l = vertx.setPeriodic(1000, 5000, id -> {
      System.out.println("Hello from Vert.x setPeriodic!");
    });

    vertx.cancelTimer(l);

    for (int i = 0; i < 10; i++) {

        int taskId = i;

        Future<String> future = vertx.executeBlocking(() -> {

          System.out.println("Hello from Vert.x! Task " + taskId);

          try
          {
            Thread.sleep(5000);
          }
          catch (InterruptedException e)
          {
            throw new RuntimeException(e);
          }

          return "Task " + taskId + " completed";

        }, true);

        // Handle the result of each task
        future.onComplete(res -> {

          if (res.succeeded())
          {
            System.out.println("Result: " + res.result());
          }
          else
          {
            System.out.println("Error: " + res.cause());
          }
        });
      }

    }

}
