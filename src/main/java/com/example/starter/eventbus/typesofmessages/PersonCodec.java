package com.example.starter.eventbus.typesofmessages;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;

public class PersonCodec implements MessageCodec<Person, Person> {

  @Override
  public void encodeToWire(Buffer buffer, Person person)
  {
    // Convert the Person to a JSON representation.
    JsonObject jsonToEncode = new JsonObject();

    jsonToEncode.put("name", person.getName());

    jsonToEncode.put("age", person.getAge());

    String jsonStr = jsonToEncode.encode();

    byte[] encoded = jsonStr.getBytes();

    // Write the length of the encoded data first.
    buffer.appendInt(encoded.length);

    // Then the actual data.
    buffer.appendBytes(encoded);
  }

  @Override
  public Person decodeFromWire(int pos, Buffer buffer)
  {
    // Read the length of the JSON data.
    int length = buffer.getInt(pos);

    pos += 4;

    // Read the bytes and convert to a string.
    byte[] bytes = buffer.getBytes(pos, pos + length);

    String jsonStr = new String(bytes);

    JsonObject content = new JsonObject(jsonStr);

    return new Person(content.getString("name"), content.getInteger("age"));
  }


  /*
  Local vs. Remote:

  For messages that are sent locally within the same JVM, the transform method is used,
  which simply returns the original object.

  For messages that travel over the network, the full encode-decode process is used.
  */
  @Override
  public Person transform(Person person)
  {
    // For local messages, we can simply return the object.
    return person;
  }

  @Override
  public String name()
  {
    //Returns a string that uniquely identifies this codec (here, "PersonCodec").
    //This is used by Vert.x to know which codec to use when encoding or decoding messages.
    return "PersonCodec";
  }

  @Override
  public byte systemCodecID()
  {
    //Returns -1 to signal that this is a custom codec, not one of the built-in types.
    return -1;
  }
}


