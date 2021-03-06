package org.scooter.bson.implicits

import io.netty.buffer.ByteBuf

import org.scooter.bson.{ Bytes, Readable, Document, Writable }
import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BsonBoolean class.
 */
object BsonBoolean extends Readable {

  /**
   * Load the Boolean value and its key from the buffer.
   *
   * @param buffer The ByteBuf.
   * @param doc The document to place in.
   */
  def read(buffer: ByteBuf, doc: Document) = {
    doc(buffer.readCString) = if (buffer.readByte == 0x01) true else false
  }
}

/**
 * Wraps booleans to provide additional behaviour around BSON serialization.
 *
 * @param target The Boolean that is wrapped.
 */
case class BsonBoolean(target: Boolean) extends BsonValue(target) {

  /**
   * Dump the boolean to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The order in which bytes must be placed booleano the buffer:
   *  - The boolean type.
   *  - The bytes for the boolean's key.
   *  - A null byte.
   *  - The boolean byte (0x01 for true, 0x00 for false).
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance boolean value.
   */
  def write(buffer: ByteBuf, key: String) = {
    buffer.writeByte(Bytes.Boolean)
    buffer.writeCString(key)
    buffer.writeByte(if (target) 0x01 else 0x00)
  }
}
