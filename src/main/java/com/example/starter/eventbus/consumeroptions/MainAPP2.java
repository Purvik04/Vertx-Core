package com.example.starter.eventbus.consumeroptions;

import io.vertx.core.Vertx;

public class MainAPP2
{
  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new SndVerticle());

    vertx.deployVerticle(new RcvVerticle());

  }
}
