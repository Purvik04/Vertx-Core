package com.example.starter.tcpserversandclients.eventbuswritehandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;

public class MessageSenderVerticle extends AbstractVerticle {

  @Override
  public void start() {
    // Periodically send a message every 5 seconds to all connected clients.
    vertx.setPeriodic(5000, timerId -> {
      if (TcpServerVerticle.writeHandlerIDs.isEmpty()) {
        System.out.println("No clients connected. Waiting for clients...");
        return;
      }
      for (String handlerID : TcpServerVerticle.writeHandlerIDs) {
        System.out.println("Sending message to handler: " + handlerID);
        Buffer message = Buffer.buffer("Hello from MessageSenderVerticle!");
        vertx.eventBus().send(handlerID, message);
      }
    });
  }
}
