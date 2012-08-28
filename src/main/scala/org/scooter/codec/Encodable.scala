package org.scooter.codec

import io.netty.buffer.ByteBuf

/**
 * Represents any object that can be encoded into bytes on a ByteBuf.
 */
trait Encodable {

  /**
   * Encode the object into a buffer that can be written to the socket.
   *
   * @param buffer The ByteBuf that will get written.
   */
  def encode(buffer: ByteBuf) : Unit
}
