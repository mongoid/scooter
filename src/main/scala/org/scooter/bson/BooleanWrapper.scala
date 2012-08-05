package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.ChannelBufferWrapper._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BooleanWrapper class.
 */
object BooleanWrapper extends Deserializable {

  /**
   * Load the Boolean value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: Document) = {
    doc(buffer.readCString) = if (buffer.readByte == 0x01) true else false
  }
}

/**
 * Wraps booleans to provide additional behaviour around BSON serialization.
 *
 * @param target The Boolean that is wrapped.
 */
class BooleanWrapper(target: Boolean) extends Serializable {

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
  def bsonDump(buffer: ChannelBuffer, key: String) = {
    buffer.writeByte(Bytes.Boolean)
    buffer.writeCString(key)
    buffer.writeByte(byteValue)

    /**
     * Get the byte value for the boolean.
     *
     * @return The Byte value of the boolean.
     */
    def byteValue: Byte = if (target) 0x01 else 0x00
  }
}
