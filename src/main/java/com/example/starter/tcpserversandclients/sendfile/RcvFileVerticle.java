package com.example.starter.tcpserversandclients.sendfile;

import io.vertx.core.AbstractVerticle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RcvFileVerticle extends AbstractVerticle {
  @Override
  public void start() {

    vertx.createNetClient()
          .connect(8080, "localhost", res -> {
            if(res.succeeded())
            {
              var socket = res.result();

              socket.handler(buff -> {

                FileOutputStream fos = null;

                try
                {
                  fos = new FileOutputStream("/home/purvik/IdeaProjectsUltimate/starter/src/main/java/com/example/starter/hello2.txt");

                  fos.write(buff.getBytes());
                }
                catch (IOException e)
                {
                  throw new RuntimeException(e);
                }
                finally {
                  try
                  {
                    fos.close();
                  }
                  catch (IOException e)
                  {
                    throw new RuntimeException(e);
                  }
                }

              });
            }
            else{
              System.out.println("Failed to connect to server: " + res.cause().getMessage());
            }
          });
  }

  @Override
  public void stop() {
    System.out.println("RcvFileVerticle stopped.");
  }
}
