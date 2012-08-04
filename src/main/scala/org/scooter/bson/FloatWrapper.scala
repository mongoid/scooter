package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer
import org.scooter.bson.Conversions._
import scala.collection.mutable.HashMap

/**
 * Companion object to the FloatWrapper class.
 */
object FloatWrapper extends Deserializable {

  /**
   * Load the Float value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: HashMap[String, Any]) = {
    doc(buffer.readCString) = buffer.readDouble
  }
}

/**
 * Wraps floats to provide additional behaviour around BSON serialization.
 *
 * @param target The Float that is wrapped.
 */
class FloatWrapper(target: Float) extends Serializable {

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
    buffer.writeByte(Bytes.FLOAT)
    buffer.writeCString(key)
    buffer.writeDouble(target)
  }
}
