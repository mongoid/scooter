package org.scooter.protocol

import io.netty.buffer.ByteBuf

/**
 * Represents objects that can be decoded from bytes into Reply objects.
 */
trait Decodable {

  /**
   * Deencode the bytes in the buffer to a Reply.
   *
   * @param buffer The ByteBuf that will get decoded.
   *
   * @return The Reply.
   */
  def decode(buffer: ByteBuf) : Reply
}
