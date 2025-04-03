package com.example.starter.verticles.virtualverticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;

import java.util.concurrent.TimeUnit;

public class VirtualVerticleDemo {

  public static void main(String[] args)
  {

    Vertx vertx = Vertx.vertx();

    DeploymentOptions options = new DeploymentOptions().setThreadingModel(ThreadingModel.VIRTUAL_THREAD);

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
