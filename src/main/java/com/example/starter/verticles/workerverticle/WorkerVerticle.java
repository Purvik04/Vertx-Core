package com.example.starter.verticles.workerverticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;

public class WorkerVerticle {

  public static void main(String[] args)
  {

    Vertx vertx = Vertx.vertx();

    DeploymentOptions options = new DeploymentOptions().setThreadingModel(ThreadingModel.WORKER).setWorkerPoolName("hi")
        .setInstances(1) // Number of instances to deploy
        .setWorkerPoolSize(10)
        .setMaxWorkerExecuteTime(10L)
        .setMaxWorkerExecuteTimeUnit(java.util.concurrent.TimeUnit.SECONDS)
        .setWorkerPoolName("my-worker-pool");

    String[] verticleID = new String[1];

    vertx.deployVerticle("com.example.starter.verticles.MyVerticle", options, res -> {
      if (res.succeeded())
      {
        System.out.println("MyVerticle deployed successfully");

        verticleID[0] = res.result();
      }
      else
      {
        System.out.println("Failed to deploy MyVerticle: " + res.cause());
      }
    });

    vertx.undeploy(verticleID[0]);
  }
}
