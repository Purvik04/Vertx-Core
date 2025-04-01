package com.example.starter;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class FutureComposition
{
  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(10));

    Future<String> future = vertx.executeBlocking(() -> {

      Thread.sleep(1000);

      return "Hello from Vert.x!";

    }, true)
      .compose(res ->{

      System.out.println(res);

      return Future.succeededFuture("Hello from Vert.x!");
    })
      .compose(res ->{

      System.out.println(Thread.currentThread().getName());

      System.out.println(res);

      return Future.succeededFuture("Hello from Vert 3");
    })
      .andThen(res->{

      System.out.println(Thread.currentThread().getName() + " first andthen");

      System.out.println(res.result());
    })
      .andThen(res->{

      System.out.println(Thread.currentThread().getName() + " second andthen");

      System.out.println(res.result());
    });

  }
}
