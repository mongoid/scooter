package org.scooter.codec

import io.netty.buffer.ByteBuf

/**
 * Represents objects that can be decoded from bytes into objects of the
 * parameterized type.
 */
trait Decodable[+T] {

  /**
   * Deencode the bytes in the buffer to a Reply.
   *
   * @param buffer The ByteBuf that will get decoded.
   *
   * @return The interface's type parameter.
   */
  def decode(buffer: ByteBuf) : T
}
