package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer

/**
 * All Messages sent to the database inherit from this.
 *
 * @param code The operation code.
 */
abstract class Message(code: Int) extends Serializable {

  /**
   * Get the header for this message.
   *
   * @return The Header.
   */
  val header = Header(0, 0, code)
}
