package org.scooter.bson.implicits

import io.netty.buffer.ByteBuf

import org.scooter.bson.{ Bytes, Readable, Document, Writable }
import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BsonDouble class.
 */
object BsonDouble extends Readable {

  /**
   * Load the Double value and its key from the buffer.
   *
   * @param buffer The ByteBuf.
   * @param doc The document to place in.
   */
  def bsonRead(buffer: ByteBuf, doc: Document) = {
    doc(buffer.readCString) = buffer.readDouble
  }
}

/**
 * Wraps doubles to provide additional behaviour around BSON serialization.
 *
 * @param target The Double that is wrapped.
 */
case class BsonDouble(target: Double) extends Writable {

  /**
   * Dump the double to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The order in which bytes must be placed doubleo the buffer:
   *  - The double type.
   *  - The bytes for the double's key.
   *  - A null byte.
   *  - The double.
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance double value.
   */
  def bsonWrite(buffer: ByteBuf, key: String) = {
    buffer.writeByte(Bytes.Double)
    buffer.writeCString(key)
    buffer.writeDouble(target)
  }
}
