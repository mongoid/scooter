package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer

/**
 * Represents a generic database Command.
 */
case class Command extends Message {

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
   * @param buffer The ChannelBuffer that will get written.
   */
  def serialize(buffer: ChannelBuffer) = {}
}
