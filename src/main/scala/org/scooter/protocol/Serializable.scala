package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer

trait Serializable {

  /**
   * Serialize the object into a buffer that can be written to the socket.
   *
   * @param buffer The ChannelBuffer that will get written.
   */
  def serialize(buffer: ChannelBuffer) : Unit
}
