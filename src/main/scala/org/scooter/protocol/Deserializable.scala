package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer

/**
 * Represents objects that can be deserialized from bytes into Reply objects.
 */
trait Deserializable {

  /**
   * Deserialize the bytes in the buffer to a Reply.
   *
   * @param buffer The ChannelBuffer that will get deserialized.
   *
   * @return The Reply.
   */
  def deserialize(buffer: ChannelBuffer) : Reply
}
