package com.example.starter.filesystem;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.*;

public class FSDemo {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();


    FileSystem fs = vertx.fileSystem();

    fs.writeFile("src/main/java/com/example/starter/hello2.txt",
        Buffer.buffer("Hello"))
      .onComplete(result -> {
        if (result.succeeded()) {
          System.out.println("File written");
        } else {
          System.err.println("Oh oh ..." + result.cause());
        }
      });

    fs.readFile("src/main/java/com/example/starter/hello2.txt")
      .onComplete(res -> {
        if (res.succeeded())
        {
          System.out.println("File content: " + res.result().toString());
        }
        else
        {
          System.out.println("Failed to read file: " + res.cause());
        }
      });


    fs.copy("src/main/java/com/example/starter/hello2.txt",
        "src/main/java/com/example/starter/hello1.txt",
        new CopyOptions().setReplaceExisting(true))
      .onComplete(res -> {
        if (res.succeeded())
        {
          System.out.println("File copied successfully" );
        }
        else
        {
          System.out.println("Failed to copy file: " + res.cause());
        }
      });

    fs.exists("src/main/java/com/example/starter/hello3.txt")
      .compose(exist -> {
        if (exist)
        {
          return vertx.fileSystem().delete("target/classes/junk.txt");
        }
        else
        {
          return Future.failedFuture("File does not exist");
        }
      }).onComplete(result -> {
        if (result.succeeded())
        {
          System.out.println("File deleted");
        }
        else
        {
          System.err.println("Oh oh ... - cannot delete the file: " + result.cause().getMessage());
        }
      });

//     Copy file from foo.txt to bar.txt synchronously
//    fs.copyBlocking("foo.txt", "bar.txt");

    OpenOptions options = new OpenOptions().setAppend(true).setPerms("rwxrwxrwx");

    fs.open("src/main/java/com/example/starter/hello1.txt", options)
      .onComplete(res -> {
        if (res.succeeded())
        {
          AsyncFile file = res.result();

          file.write(Buffer.buffer("Hello from outisde!"));

          file.close();
        }
        else
        {
          System.out.println("Failed to open file: " + res.cause());
        }
      });

  }
}
