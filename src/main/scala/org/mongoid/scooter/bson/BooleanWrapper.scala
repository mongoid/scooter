package org.mongoid.scooter.bson

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
  def bsonDump(buffer: MutableBuffer, key: String) = {
    buffer.
      putByte(Bytes.BOOLEAN).
      putString(key).
      putByte(Bytes.NULL).
      putByte(byteValue)
  }

  /**
   * Get the byte value for the boolean.
   *
   * @return The Byte value of the boolean.
   */
  private def byteValue: Byte = if (target) 0x01 else 0x00
}
