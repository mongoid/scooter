package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.Conversions._

/**
 * Companion object to the StringWrapper class.
 */
object StringWrapper extends Deserializable {

  /**
   * Load the string value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: Document) = {
    doc(buffer.readCString) = buffer.readString
  }
}

/**
 * Wraps strings to provide additional behaviour around BSON serialization.
 *
 * @param target The String that is wrapped.
 */
class StringWrapper(target: String) extends Serializable {

  /**
   * Dump the string to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The string type.
   *  - The bytes for the string's key.
   *  - A null byte.
   *  - The length of the string plus 1.
   *  - The string.
   *  - A null byte.
   *
   * @param buffer The buffer being written to.
   * @param key The string key to this instance string value.
   */
  def bsonDump(buffer: ChannelBuffer, key: String) = {
    buffer.writeByte(Bytes.String)
    buffer.writeCString(key)
    buffer.writeString(target)
  }
}
