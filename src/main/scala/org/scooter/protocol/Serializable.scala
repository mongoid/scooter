package org.scooter.protocol

import io.netty.buffer.ByteBuf

/**
 * Represents any object that can be serialized into bytes on a ByteBuf.
 */
trait Serializable {

  /**
   * Serialize the object into a buffer that can be written to the socket.
   *
   * @param buffer The ByteBuf that will get written.
   */
  def serialize(buffer: ByteBuf) : Unit
}
