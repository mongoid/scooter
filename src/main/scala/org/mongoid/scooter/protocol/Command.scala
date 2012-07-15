package org.mongoid.scooter.protocol

import java.nio.ByteBuffer

/**
 * Represents a generic database Command.
 */
class Command extends Message {

  /**
   * Get the operation code for a command..
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @return The Integer operation code.
   */
  def operationCode = 1000

  /**
   * Serialize the Command into a buffer that can be written to the socket.
   *
   * @param buffer The ByteBuffer that will get written.
   */
  def serialize(buffer: ByteBuffer) = {}
}
