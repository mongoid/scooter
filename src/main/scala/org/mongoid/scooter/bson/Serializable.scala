package org.mongoid.scooter.bson

import java.nio.ByteBuffer

object Serializable {

  /**
   * Get a NULL byte. (\x00)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of null.
   */
  final val NULL: Byte = 0x00
}

trait Serializable {

  /**
   * Dump the value to the buffer in it's proper BSON format.
   *
   * @param buffer The buffer being written to.
   * @param key The string key to this instance string value.
   */
  def bsonDump(buffer: ByteBuffer, key: String) : Unit
}
