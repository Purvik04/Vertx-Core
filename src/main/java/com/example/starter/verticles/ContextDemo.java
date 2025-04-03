package com.example.starter.verticles;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;

public class ContextDemo extends AbstractVerticle {

  @Override
  public void start()
  {
    System.out.println("Verticle Deploying started");

    Context vertxContext = vertx.getOrCreateContext();

    System.out.println("Running on context: " + context.config().getString("name2"));

    // Check if the current thread is the event loop thread
    if (vertxContext.isEventLoopContext())
    {
      System.out.println("This is the event loop thread.");
    }
    else if (vertxContext.isWorkerContext())
    {
      System.out.println("This is the worker pool thread.");
    }
    else {
      System.out.println("This is not the event loop thread.");
    }

    context.put("name","purvik");

    context.runOnContext(v->{
      System.out.println("Running on context: " + context.get("name"));
    });
  }

  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx();

    JsonObject config = new JsonObject().put("name2", "purvik2");

    vertx.deployVerticle(new ContextDemo(), new DeploymentOptions().setThreadingModel(ThreadingModel.WORKER).setConfig(config));
  }

}
