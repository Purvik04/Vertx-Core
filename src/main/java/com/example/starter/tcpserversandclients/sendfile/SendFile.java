package com.example.starter.tcpserversandclients.sendfile;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;

import java.io.IOException;
import java.io.InputStream;

public class SendFile {
  public static void main(String[] args) throws InterruptedException {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(SendFileVericle.class.getName());

    Thread.sleep(1000);

    vertx.deployVerticle(RcvFileVerticle.class.getName());

  }
}
