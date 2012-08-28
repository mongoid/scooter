package org.scooter.protocol

import java.util.concurrent.atomic.AtomicInteger
import java.util.Random

import io.netty.buffer.ByteBuf

import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.codec.Encodable

/**
 * Companion object to the Header class.
 */
object Header {

  /**
   * Gets the atomic counter for incrementing request ids.
   *
   * @return The AtomicInteger.
   */
  final lazy val Inc = new AtomicInteger((new Random).nextInt)

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

/**
 * Represents the standard header that is sent to MongoDB in all messages.
 *
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param length The length of the message.
 * @param request The id of the request.
 * @param original The id of the original request.
 * @param code The operation code.
 */
case class Header(
  val length: Int, val request: Int, val original: Int, val code: Int
) extends Encodable {

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
   * @param buffer The ByteBuf that will get written.
   */
  def encode(buffer: ByteBuf) = {
    buffer.writeInts(length, request, original, code)
  }
}
