package com.example.starter.buffer;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;

public class BufferDemo {
  public static void main(String[] args) {

    // Create an empty Buffer instance
    Buffer buffer = Buffer.buffer();

    Buffer buffer2 = Buffer.buffer("Hello World");

    Buffer buffer3 = Buffer.buffer("Hello World" , "UTF-16");

    System.out.println(buffer3.length());

    byte[] bytes = new byte[] {1, 3, 5};
    Buffer buffer4 = Buffer.buffer(bytes);

    //Create a buffer with an initial size hint.
    Buffer buff = Buffer.buffer(10000);

    Buffer buffer5 = Buffer.buffer()
      .appendInt("Hello Bhai kemo 6o".length()) // Store the length of the first string
      .appendString("Hello Bhai kemo 6o")      // Append the actual first string
      .appendInt(42)                           // Append integer
      .appendInt("Maja ma bhai".length())      // Store the length of the second string
      .appendString("Maja ma bhai");

    Vertx vertx = Vertx.vertx();

    Buffer original = Buffer.buffer("Hello Bhai");

    // Using copy()
    Buffer copiedBuffer = original.copy();
    copiedBuffer.setString(0, "Hiiii");  // Modify the copy

    System.out.println("Original: " + original.toString());  // Original remains unchanged
    System.out.println("Copied: " + copiedBuffer.toString());  // Copy has new content

    // Using slice()
    Buffer slicedBuffer = original.slice(0, 5);  // Slice "Hello"
    slicedBuffer.setString(0, "Yelo");  // Modify the slice

    System.out.println("Original: " + original.toString());  // Original is also modified
    System.out.println("Sliced: " + slicedBuffer.toString());  // Sliced reflects the change

    vertx.deployVerticle(RcvBufferVerticle.class.getName());

    vertx.setTimer(1000, id -> vertx.eventBus().publish("address", buffer5));

  }
}
