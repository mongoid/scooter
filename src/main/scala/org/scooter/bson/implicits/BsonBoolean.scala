package org.scooter.bson.implicits

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

import org.scooter.bson.{ Bytes, Loadable, Document, Dumpable }
import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BsonBoolean class.
 */
object BsonBoolean extends Loadable {

  /**
   * Load the Boolean value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: Buffer, doc: Document) = {
    doc(buffer.readCString) = if (buffer.readByte == 0x01) true else false
  }
}

/**
 * Wraps booleans to provide additional behaviour around BSON serialization.
 *
 * @param target The Boolean that is wrapped.
 */
case class BsonBoolean(target: Boolean) extends Dumpable {

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
  def bsonDump(buffer: Buffer, key: String) = {
    buffer.writeByte(Bytes.Boolean)
    buffer.writeCString(key)
    buffer.writeByte(if (target) 0x01 else 0x00)
  }
}
