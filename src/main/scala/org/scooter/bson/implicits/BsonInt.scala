package org.scooter.bson.implicits

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.{ Bytes, Deserializable, Document, Serializable }
import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BsonInt class.
 */
object BsonInt extends Deserializable {

  /**
   * Load the Int value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: Document) = {
    doc(buffer.readCString) = buffer.readInt
  }
}

/**
 * Wraps ints to provide additional behaviour around BSON serialization.
 *
 * @param target The Int that is wrapped.
 */
class BsonInt(target: Int) extends Serializable {

  /**
   * Dump the int to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The int type.
   *  - The bytes for the int's key.
   *  - A null byte.
   *  - The int.
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance int value.
   */
  def bsonDump(buffer: ChannelBuffer, key: String) = {
    buffer.writeByte(Bytes.Int32)
    buffer.writeCString(key)
    buffer.writeInt(target)
  }
}
