package org.scooter.protocol

import org.scooter.bson.MutableBuffer

/**
 * All Messages sent to the database should mix in this trait.
 */
trait Message {

  /**
   * Get the operation code for the message.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @return The Integer operation code.
   */
  def operationCode: Int

  /**
   * Serialize the Message into a buffer that can be written to the socket.
   *
   * @param buffer The MutableBuffer that will get written.
   */
  def serialize(buffer: MutableBuffer) : Unit

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
   * @param buffer The MutableBuffer that will get written.
   */
  def serializeHeader(buffer: MutableBuffer) = {
    buffer.
      putInt(0).
      putInt(0).
      putInt(0).
      putInt(operationCode)
  }
}
