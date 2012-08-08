package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer

/**
 * All Messages sent to the database should mix in this trait.
 */
abstract class Message(code: Int) {

  /**
   * Serialize the Message into a buffer that can be written to the socket.
   *
   * @param buffer The ChannelBuffer that will get written.
   */
  def serialize(buffer: ChannelBuffer) : Unit

  /**
   * Serializes the header of the message.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The length of the message.
   *  - The request id.
   *  - The id of the original message.
   *  - The operation code.
   *
   * @param buffer The ChannelBuffer that will get written.
   */
  def serializeHeader(buffer: ChannelBuffer) = {
    buffer.writeInt(0)
    buffer.writeInt(0)
    buffer.writeInt(0)
    buffer.writeInt(code)
  }
}
