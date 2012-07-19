package org.mongoid.scooter.bson

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
  def bsonDump(buffer: MutableBuffer, key: String) = {
    buffer.
      putByte(Bytes.FLOAT).
      putString(key).
      putByte(Bytes.NULL).
      putDouble(target)
  }
}
