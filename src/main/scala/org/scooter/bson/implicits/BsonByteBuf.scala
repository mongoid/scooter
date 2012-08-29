package org.scooter.bson.implicits

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufIndexFinder.NUL

import org.scooter.bson.{ Bytes, Document, ObjectId }
import org.scooter.functional.Utilities._
import org.scooter.protocol.Header

import scala.language.implicitConversions

/**
 * Companion object to the BsonByteBuf.
 */
object BsonByteBuf {

  /**
   * Implicit conversion from a ByteBuf to a BsonByteBuf.
   *
   * @param target The ByteBuf that is getting wrapped.
   *
   * @return The BsonByteBuf.
   */
  implicit def wrapBuffer(target: ByteBuf): BsonByteBuf = {
    new BsonByteBuf(target)
  }
}

/**
 * Wraps the ByteBuf to provide additional read operations with respect
 * to BSON deserialization specifics.
 *
 * @param target The wrapped ByteBuf.
 */
case class BsonByteBuf(target: ByteBuf) {

  /**
   * Is the buffer readable - ie is an entire message frame present.
   *
   * @return If the buffer is readable.
   */
  def isReadable = !notReadable

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

  def readDocument = {
    (new Document).tap {
      doc => {
        val length = target.readInt
        readPair(target.readByte, doc)
      }
    }
  }

  /**
   * Read a message frame from the buffer.
   *
   * @note This gets the length of the message, and slices that from the
   *  buffer and returns only the bytes for a single message.
   *
   * @return The message frame as a ByteBuf.
   */
  def readFrame: ByteBuf = {
    val length = target.getInt(0)
    val index = target.readerIndex
    val frame = target.slice(index, length)
    target.readerIndex(index + length)
    return frame
  }

  /**
   * Read a Header from the ByteBuf.
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
   * Write the document to the buffer.
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The length of the document in bytes.
   *  - The document.
   *  - A null byte.
   *  - The length of the entire message.
   *
   * @param document The Document to write.
   */
  def writeDocument(document: Document) = {
    val start = target.writerIndex
    target.writeInt(0)
    document.foreach(pair => pair._2.write(target, pair._1))
    target.writeZero(1)
    target.setInt(start, target.writerIndex - start)
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
   * Is a standard message frame not able to be read?
   *
   * @return If the frame isn't readable.
   */
  private def frameNotReadable = target.readableBytes < target.getInt(0)

  /**
   * Is the length of the frame not present - the first 4 bytes.
   *
   * @return If the length is not large enough to read a length.
   */
  private def lengthNotReadable = target.readableBytes < 4

  /**
   * Is the buffer not readable - if the frame is not present in it's
   * entirety.
   *
   * @return If the buffer is not readable.
   */
  private def notReadable = lengthNotReadable || frameNotReadable

  /**
   * Recursive function to load all the key/value pairs that
   * are in the buffer.
   *
   * @note This function operates by:
   *   - Look at the provided byte to get the type of object.
   *   - Get the Readable for that type, and load the bytes.
   *   - Read the next byte.
   *
   * @param buffer The ByteBuf to read from.
   * @param byte The Byte representing the value type or zero.
   * @param doc The Document being written into.
   */
  private def readPair(byte: Byte, doc: Document): Unit = {
    if (byte != Bytes.Null) {
      Bytes.getCompanion(byte).read(target, doc)
      readPair(target.readByte, doc)
    }
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
