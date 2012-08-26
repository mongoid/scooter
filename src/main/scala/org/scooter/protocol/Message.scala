package org.scooter.protocol

import io.netty.buffer.ByteBuf

/**
 * All Messages sent/received to/from the database inherit from this class.
 *
 * @param header The Header.
 * @param code The operation code.
 */
abstract class Message(header: Header, code: Int) {

  /**
   * Serialize a header then process the rest of the message's
   * serialization.
   *
   * @example Serialize with a header.
   *  withHeader(buffer) {
   *    buffer.writeCString(name)
   *  }
   *
   * @param buffer The ByteBuf to write to.
   * @param func The function to execute.
   */
  def withHeader(buffer: ByteBuf)(func: => Unit) = {
    header.encode(buffer)
    func
    buffer.setInt(0, buffer.writerIndex)
  }
}
