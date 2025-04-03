package com.example.starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;

public class AsyncStartStop extends AbstractVerticle {

  private HttpServer server;

  @Override
  public void start(Promise<Void> startPromise)
  {
    server = vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    });

    // Start the server asynchronously:
    server.listen(8080)
      .<Void> mapEmpty()  // Convert the result to a Future<Void>
      .onComplete(startPromise);  // Signal when the server is started

    System.out.println("Hello form async start!");
  }

  @Override
  public void stop(Promise<Void> stopPromise) {
    doSomethingThatTakesTime()
      .<Void>mapEmpty()
      .onComplete(stopPromise);  // Signal when cleanup is done
  }

  public static Future<String> doSomethingThatTakesTime(){
    return Future.succeededFuture("Done");
  }
}
