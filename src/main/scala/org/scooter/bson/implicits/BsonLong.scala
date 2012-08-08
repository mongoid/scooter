package org.scooter.bson.implicits

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.{ Bytes, Loadable, Document, Dumpable }
import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BsonLong class.
 */
object BsonLong extends Loadable {

  /**
   * Load the string value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: Document) = {
    doc(buffer.readCString) = buffer.readLong
  }
}

/**
 * Wraps ints to provide additional behaviour around BSON serialization.
 *
 * @param target The Long that is wrapped.
 */
case class BsonLong(target: Long) extends Dumpable {

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
  def bsonDump(buffer: ChannelBuffer, key: String) = {
    buffer.writeByte(Bytes.Int64)
    buffer.writeCString(key)
    buffer.writeLong(target)
  }
}
