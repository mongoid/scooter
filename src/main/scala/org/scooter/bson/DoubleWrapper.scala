package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.ChannelBufferWrapper._
import org.scooter.bson.Serialization._

/**
 * Companion object to the DoubleWrapper class.
 */
object DoubleWrapper extends Deserializable {

  /**
   * Load the Double value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: Document) = {
    doc(buffer.readCString) = buffer.readDouble
  }
}

/**
 * Wraps doubles to provide additional behaviour around BSON serialization.
 *
 * @param target The Double that is wrapped.
 */
class DoubleWrapper(target: Double) extends Serializable {

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
  def bsonDump(buffer: ChannelBuffer, key: String) = {
    buffer.writeByte(Bytes.Float)
    buffer.writeCString(key)
    buffer.writeDouble(target)
  }
}
