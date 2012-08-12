package org.scooter.bson.implicits

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }
import org.jboss.netty.buffer.ChannelBufferIndexFinder.NUL

import org.scooter.bson.ObjectId
import org.scooter.protocol.Header

import scala.language.implicitConversions

/**
 * Companion object to the BsonChannelBuffer.
 */
object BsonChannelBuffer {

  /**
   * Implicit conversion from a ChannelBuffer to a BsonChannelBuffer.
   *
   * @param target The ChannelBuffer that is getting wrapped.
   *
   * @return The BsonChannelBuffer.
   */
  implicit def wrapBuffer(target: Buffer): BsonChannelBuffer = {
    new BsonChannelBuffer(target)
  }
}

/**
 * Wraps the ChannelBuffer to provide additional read operations with respect
 * to BSON deserialization specifics.
 *
 * @param target The wrapped ChannelBuffer.
 */
case class BsonChannelBuffer(target: Buffer) {

  /**
   * Read a C String from the buffer.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The bytes that are getting read:
   *  - The bytes up to the next null byte (0x00).
   *  - The null byte itself to increment the read index.
   *
   * @return The String.
   */
  def readCString = readStringBytes(target.bytesBefore(NUL))

  /**
   * Read a Header from the ChannelBuffer.
   *
   * @return The message Header.
   */
  def readHeader = {
    new Header(
      target.readInt,
      target.readInt,
      target.readInt,
      target.readInt
    )
  }

  /**
   * Read an object id from the buffer.
   *
   * @todo: Temporary.
   *
   * @return The ObjectId.
   */
  def readObjectId = {
    new ObjectId(target.readInt, target.readInt, target.readInt)
  }

  /**
   * Read a String from the buffer.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The bytes that are getting read:
   *  - The integer bytes for the length of the string.
   *  - The bytes for the string for the specified length.
   *  - The null byte that follows the end of the string.
   *
   * @return The String.
   */
  def readString = readStringBytes(target.readInt - 1)

  /**
   * Write a c string to the buffer.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The bytes that are getting written:
   *  - The bytes for the string.
   *  - A null byte.
   */
  def writeCString(string: String) = {
    target.writeBytes(string.getBytes)
    target.writeZero(1)
  }

  /**
   * Writes all the provided integers to the buffer in order.
   *
   * @param ints A sequence of Ints to write.
   */
  def writeInts(ints: Int*) = ints.foreach(int => target.writeInt(int))

  /**
   * Write a string to the buffer.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The bytes that are getting written:
   *  - The length of the string in bytes.
   *  - The bytes for the string.
   *  - A null byte.
   */
  def writeString(string: String) = {
    target.writeInt(string.length + 1)
    target.writeBytes(string.getBytes)
    target.writeZero(1)
  }

  /**
   * Read the bytes for a String of the specified length plus the trailing
   * null byte and return the String.
   *
   * @param length The length of the String.
   *
   * @return The String.
   */
  private def readStringBytes(length: Int) = {
    val buffer = target.readBytes(length)
    target.readByte
    new String(buffer.array)
  }
}
