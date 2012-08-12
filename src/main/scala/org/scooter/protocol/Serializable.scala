package org.scooter.protocol

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

/**
 * Represents any object that can be serialized into bytes on a ChannelBuffer.
 */
trait Serializable {

  /**
   * Serialize the object into a buffer that can be written to the socket.
   *
   * @param buffer The ChannelBuffer that will get written.
   */
  def serialize(buffer: Buffer) : Unit
}
