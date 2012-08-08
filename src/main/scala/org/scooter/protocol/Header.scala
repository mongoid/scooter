package org.scooter.protocol

import java.util.concurrent.atomic.AtomicInteger

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.implicits.BsonChannelBuffer._

object Header {

  /**
   * Gets the atomic counter for incrementing request ids.
   *
   * @return The AtomicInteger.
   */
  final lazy val Inc = new AtomicInteger(0)

  /**
   * Instantiate a new header, auto increments the global counter.
   *
   * @param length The length of the message.
   * @param original The original request id.
   * @param code The operation code.
   *
   * @return The Header.
   */
  def apply(length: Int, original: Int, code: Int): Header = {
    Header(length, Inc.getAndIncrement, original, code)
  }
}

case class Header(length: Int, request: Int, original: Int, code: Int)
  extends Serializable {

  /**
   * Serializes the header.
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
  def serialize(buffer: ChannelBuffer) = {
    buffer.writeInts(length, request, original, code)
  }
}
