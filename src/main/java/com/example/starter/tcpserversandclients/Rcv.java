package com.example.starter.tcpserversandclients;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Rcv {
  public static void main(String[] args)
  {
    try
    {
      Socket socket = new Socket("localhost", 8080);

      DataOutputStream out = new DataOutputStream(socket.getOutputStream());

      DataInputStream in = new DataInputStream(socket.getInputStream());

      out.writeUTF("Hello from Client");

      out.flush();

      String s = in.readUTF();

      System.out.println("Received: " + s);

      Thread.sleep(500000);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    catch (InterruptedException e)
    {
      throw new RuntimeException(e);
    }
  }
}
