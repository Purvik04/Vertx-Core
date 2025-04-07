package com.example.starter.exception;

import io.vertx.core.AbstractVerticle;

public class FailVerticle extends AbstractVerticle {
  @Override
  public void start() {
    // Simulate a failure
    throw new RuntimeException("Simulated failure in FailVerticle");
  }

  @Override
  public void stop() {
    // Cleanup code if needed
  }
}
