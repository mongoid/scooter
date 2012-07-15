package org.mongoid.scooter.bson

import java.nio.ByteBuffer

/**
 * Wraps strings to provide additional behaviour around BSON serialization.
 *
 * @param target The String that is wrapped.
 */
class StringWrapper(target: String) extends Serializable {

  /**
   * Get the type for a String. (\x02)
   *
   * @link http://bsonspec.org/#/specification
   * @return The byte representation of a UTF-8 string.
   */
  final val TYPE: Byte = 0x02

  /**
   * Dump the string to the buffer in it's proper BSON format.
   *
   * @param buffer The buffer being written to.
   * @param key The string key to this instance string value.
   */
  def bsonDump(buffer: ByteBuffer, key: String) = {
    val bytes = target.getBytes
    buffer.
      put(TYPE).                // String type.
      put(key.getBytes).        // Raw bytes of the key
      put(NULL).                // Null byte.
      putInt(bytes.length + 1). // Length of the value + 1
      put(bytes).               // The value.
      put(NULL)                 // Null byte.
  }
}
