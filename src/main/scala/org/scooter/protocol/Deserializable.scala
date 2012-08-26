package org.scooter.protocol

import io.netty.buffer.ByteBuf

/**
 * Represents objects that can be deserialized from bytes into Reply objects.
 */
trait Deserializable {

  /**
   * Deserialize the bytes in the buffer to a Reply.
   *
   * @param buffer The ByteBuf that will get deserialized.
   *
   * @return The Reply.
   */
  protected[scooter] def deserialize(buffer: ByteBuf) : Reply
}
