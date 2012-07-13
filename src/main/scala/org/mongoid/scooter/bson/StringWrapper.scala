package org.mongoid.scooter.bson

import java.nio.ByteBuffer

/**
 * Companion object for the StringWrapper class.
 */
object StringWrapper {

  /**
   * Implicit conversion from a String to a StringWrapper.
   *
   * @param target The String that is getting wrapped.
   * @return The StringWrapper around the String.
   */
  implicit def wrap(target: String) : StringWrapper = new StringWrapper(target)
}

/**
 * Wraps strings to provide additional behaviour around BSON serialization.
 */
class StringWrapper(target: String) extends Serializable {

  /**
   * See: http://bsonspec.org/#/specification
   *
   * @return The byte representation of a UTF-8 string.
   */
  final val TYPE : Byte = 2

  /**
   * Dump the string to the buffer in it's proper BSON format.
   *
   * @param buffer The buffer being written to.
   * @param key The string key to this instance string value.
   */
  def bsonDump(buffer: ByteBuffer, key: String) = {
    buffer.
      put(TYPE).
      putInt(target.length).
      put(target.getBytes).
      putInt(0)
  }
}
