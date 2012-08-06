package org.scooter.bson.implicits

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.{ Bytes, Deserializable, Document, Serializable }
import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BsonFloat class.
 */
object BsonFloat extends Deserializable {

  /**
   * Load the Float value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: Document) = {
    doc(buffer.readCString) = buffer.readDouble
  }
}

/**
 * Wraps floats to provide additional behaviour around BSON serialization.
 *
 * @param target The Float that is wrapped.
 */
class BsonFloat(target: Float) extends Serializable {

  /**
   * Dump the float to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The order in which bytes must be placed floato the buffer:
   *  - The float type.
   *  - The bytes for the float's key.
   *  - A null byte.
   *  - The float.
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance float value.
   */
  def bsonDump(buffer: ChannelBuffer, key: String) = {
    buffer.writeByte(Bytes.Float)
    buffer.writeCString(key)
    buffer.writeDouble(target)
  }
}
