package org.mongoid.scooter.protocol

import java.nio.ByteBuffer

/**
 * All Messages sent to the database should mix in this trait.
 */
trait Message {

  /**
   * Get the operation code for the message.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   * @return The Integer operation code.
   */
  def operationCode: Int

  /**
   * Serialize the Message into a buffer that can be written to the socket.
   *
   * @param buffer The ByteBuffer that will get written.
   */
  def serialize(buffer: ByteBuffer) : Unit

  /**
   * Serializes the header of the message.
   *
   * @param buffer The ByteBuffer that will get written.
   */
  protected def serializeHeader(buffer: ByteBuffer) = {
    buffer.
      putInt(0).            // Placeholder for message length.
      putInt(0).            // Request id.
      putInt(0).            // Response to.
      putInt(operationCode) // Operation code.
  }
}
