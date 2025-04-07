package com.example.starter.buffer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;

public class RcvBufferVerticle extends AbstractVerticle {

  @Override
  public void start()
  {
    vertx.eventBus().consumer("address", message -> {

      Buffer receivedBuffer = (Buffer) message.body();

      int offset = 0;  // Keep track of reading position

      // Read first string
      int firstStringLength = receivedBuffer.getInt(offset);  // Read length of first string

      offset += 4;  // Move to actual string

      String firstString = receivedBuffer.getString(offset, offset + firstStringLength);

      offset += firstStringLength;  // Move past the first string

      // Read integer
      int intValue = receivedBuffer.getInt(offset);

      offset += 4;  // Move past the integer

      // Read second string
      int secondStringLength = receivedBuffer.getInt(offset);  // Read length of second string

      offset += 4;  // Move to actual string

      String secondString = receivedBuffer.getString(offset, offset + secondStringLength);

      System.out.println("First String: " + firstString);

      System.out.println("Integer Value: " + intValue);

      System.out.println("Second String: " + secondString);
    });
  }

  @Override
  public void stop() {
    // Cleanup code if needed
  }
}
