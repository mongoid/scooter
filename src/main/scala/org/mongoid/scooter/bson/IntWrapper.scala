package org.mongoid.scooter.bson

/**
 * Wraps ints to provide additional behaviour around BSON serialization.
 *
 * @param target The Int that is wrapped.
 */
class IntWrapper(target: Int) extends Serializable {

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
  def bsonDump(buffer: MutableBuffer, key: String) = {
    buffer.
      putByte(Bytes.INT_32).
      putString(key).
      putByte(Bytes.NULL).
      putInt(target)
  }
}