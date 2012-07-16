package org.mongoid.scooter.bson

object Bytes {

  /**
   * Get a NULL byte. (0x00)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of null.
   */
  final val NULL: Byte = 0x00

  /**
   * Get the type for a Float. (0x01)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a Float.
   */
  final val FLOAT: Byte = 0x01

  /**
   * Get the type for a String. (0x02)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a UTF-8 string.
   */
  final val STRING: Byte = 0x02

  /**
   * Get the type for an embedded document. (0x03)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of an embedded document.
   */
  final val EMBEDDED: Byte = 0x03

  /**
   * Get the type for an Array. (0x04)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of an Array.
   */
  final val ARRAY: Byte = 0x04

  /**
   * Get the type for a Binary. (0x05)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a binary.
   */
  final val BINARY: Byte = 0x05

  /**
   * Get the type for an Object Id. (0x07)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of an object id.
   */
  final val OBJECT_ID: Byte = 0x07

  /**
   * Get the type for a bolean. (0x08)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a boolean.
   */
  final val BOOLEAN: Byte = 0x08

  /**
   * Get the type for a UTC time. (0x09)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a UTC time.
   */
  final val TIME: Byte = 0x09
}
