package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer

/**
 * Represents any message that is sent to the database.
 *
 * @param code The operation code.
 */
abstract class Request(code: Int) extends Message(code) with Serializable {

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
