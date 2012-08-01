package org.scooter.bson

object Bytes {

  /* Get the type that is represented by the byte value.
   *
   * @param byte The Byte.
   *
   * @return [ Class ] The class.
   */
  def getType(byte: Byte) : Any = byte match {
    // @todo: Check which is faster - pattern matching or pulling out of hash map.
    case 0x01 => classOf[Float]
    case 0x02 => classOf[String]
    case _    => classOf[String]
  }

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

  /**
   * Get the type for null value. (0x0A)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a null.
   */
  final val NULL_VALUE: Byte = 0x0A

  /**
   * Get the type for a regular expression. (0x0B)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a regular expression.
   */
  final val REGEX: Byte = 0x0B

  /**
   * Get the type for Javascript code. (0x0D)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of Javascript code.
   */
  final val CODE: Byte = 0x0D

  /**
   * Get the type for a Symbol. (0x0E)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a Symbol.
   */
  final val SYMBOL: Byte = 0x0E

  /**
   * Get the type for Javascript code with scope. (0x0F)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of Javascript code with scope.
   */
  final val SCOPED_CODE: Byte = 0x0F

  /**
   * Get the type for a 32bit integer. (0x10)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of the 32 bit integer.
   */
  final val INT_32: Byte = 0x10

  /**
   * Get the type for a 64bit integer. (0x12)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of the 64 bit integer.
   */
  final val INT_64: Byte = 0x12
}
