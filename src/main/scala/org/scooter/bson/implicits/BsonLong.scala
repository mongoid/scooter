package org.scooter.bson.implicits

import io.netty.buffer.ByteBuf

import org.scooter.bson.{ Bytes, Readable, Document, Writable }
import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BsonLong class.
 */
object BsonLong extends Readable {

  /**
   * Load the string value and its key from the buffer.
   *
   * @param buffer The ByteBuf.
   * @param doc The document to place in.
   */
  def bsonRead(buffer: ByteBuf, doc: Document) = {
    doc(buffer.readCString) = buffer.readLong
  }
}

/**
 * Wraps ints to provide additional behaviour around BSON serialization.
 *
 * @param target The Long that is wrapped.
 */
case class BsonLong(target: Long) extends BsonValue(target) {

  /**
   * Dump the int to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The long type.
   *  - The bytes for the long's key.
   *  - A null byte.
   *  - The long.
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance int value.
   */
  def bsonWrite(buffer: ByteBuf, key: String) = {
    buffer.writeByte(Bytes.Int64)
    buffer.writeCString(key)
    buffer.writeLong(target)
  }
}
