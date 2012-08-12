package org.scooter.protocol

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

/**
 * All Messages sent/received to/from the database inherit from this class.
 *
 * @param code The operation code.
 */
abstract class Message(head: Header, code: Int) {

  /**
   * Get the message Header.
   *
   * @return The message Header.
   */
  def header = head

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
  def withHeader(buffer: Buffer)(func: => Unit) = {
    header.serialize(buffer)
    func
    buffer.setInt(0, buffer.writerIndex)
  }
}
