package com.example.starter.verticles;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class PassingJsonObjAsOption {

  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx();

    JsonObject options = new JsonObject().put("name", "purvik")
        .put("age", 28);

    String[] verticleID = new String[1];

    DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(options);

    vertx.deployVerticle("com.example.starter.verticles.MyVerticle", deploymentOptions, res -> {
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
  }
}
