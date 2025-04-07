package com.example.starter.tcpserversandclients.client;

import io.netty.handler.logging.ByteBufFormat;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;

public class SampleTcpClient {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    NetClientOptions options = new NetClientOptions();
    options.setConnectTimeout(10000);// 10 seconds connect timeout
    options.setReconnectAttempts(5);
    options.setReconnectInterval(2000);// 2 seconds reconnect interval
    options.setLogActivity(true); //logging is not performed by Vert.x logging but by Netty
    options.setActivityLogDataFormat(ByteBufFormat.SIMPLE);
    options.setReceiveBufferSize(4*1024);

    NetClient netClient = vertx.createNetClient();

    netClient.connect(8080, "localhost", result -> {
      if (result.succeeded()) {
        System.out.println("Connected to server!");
        // You can now send data to the server using result.result().write("Your data");
      } else {
        System.out.println("Failed to connect to server: " + result.cause());
      }
    });

  }
}
