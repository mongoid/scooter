package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer

/**
 * All Messages sent to the database inherit from this.
 *
 * @param code The operation code.
 */
abstract class Message(code: Int) extends Serializable {

  /**
   * Serialize a header then process the rest of the message's
   * serialization.
   *
   * @example Serialize with a header.
   *  withHeader(buffer) {
   *    buffer.writeCString(name)
   *  }
   *
   * @param buffer The ChannelBuffer to write to.
   * @param func The function to execute.
   */
  def withHeader(buffer: ChannelBuffer)(func: => Unit) = {
    header.serialize(buffer)
    func
    buffer.setInt(0, buffer.writerIndex)
  }

  /**
   * Get the header for this message.
   *
   * @return The Header.
   */
  private lazy val header = Header(0, 0, code)
}
