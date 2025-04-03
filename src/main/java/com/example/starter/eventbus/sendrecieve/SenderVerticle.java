package com.example.starter.eventbus.sendrecieve;

import io.vertx.core.AbstractVerticle;

// SenderVerticle: Sends a message and uses the reply handler to continue the conversation.
public class SenderVerticle extends AbstractVerticle
{
  @Override
  public void start()
  {
    // Start by sending an initial message after a short delay.
    vertx.setTimer(1000, id -> sendMessage("Hello"));
  }

  // Recursive method to send a message and wait for a reply.
  private void sendMessage(String msg)
  {
    System.out.println("Sender sending: " + msg);

    vertx.eventBus().request("infinite.address", msg, reply -> {

      if (reply.succeeded())
      {
        String replyMsg = (String) reply.result().body();

        System.out.println("Sender received reply: " + replyMsg);

        // Continue the conversation by sending a follow-up message.
        sendMessage("Hello");
      }
      else
      {
        System.out.println("Failed to get a reply: " + reply.cause());
      }
    });
  }
}
