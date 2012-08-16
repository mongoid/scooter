package org.scooter.bson

import scala.collection.immutable.HashMap

import org.scooter.bson.implicits._
import org.scooter.functional.Utilities._

object Bytes {

  /* Get the type that is represented by the byte value.
   *
   * @param byte The Byte.
   *
   * @return The wrapper class.
   */
  def getCompanion(byte: Byte) = mappings(byte)

  /**
   * Get a NULL byte. (0x00)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of null.
   */
  final val Null: Byte = 0x00

  /**
   * Get the type for a Double. (0x01)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a Double.
   */
  final val Double: Byte = 0x01

  /**
   * Get the type for a String. (0x02)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a UTF-8 string.
   */
  final val String: Byte = 0x02

  /**
   * Get the type for an embedded document. (0x03)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of an embedded document.
   */
  final val Embedded: Byte = 0x03

  /**
   * Get the type for an Array. (0x04)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of an Array.
   */
  final val Array: Byte = 0x04

  /**
   * Get the type for a Binary. (0x05)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a binary.
   */
  final val Binary: Byte = 0x05

  /**
   * Get the type for an Object Id. (0x07)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of an object id.
   */
  final val ObjectId: Byte = 0x07

  /**
   * Get the type for a bolean. (0x08)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a boolean.
   */
  final val Boolean: Byte = 0x08

  /**
   * Get the type for a UTC time. (0x09)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a UTC time.
   */
  final val Time: Byte = 0x09

  /**
   * Get the type for null value. (0x0A)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a null.
   */
  final val NullValue: Byte = 0x0A

  /**
   * Get the type for a regular expression. (0x0B)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a regular expression.
   */
  final val Regex: Byte = 0x0B

  /**
   * Get the type for Javascript code. (0x0D)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of Javascript code.
   */
  final val Code: Byte = 0x0D

  /**
   * Get the type for a Symbol. (0x0E)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a Symbol.
   */
  final val Symbol: Byte = 0x0E

  /**
   * Get the type for Javascript code with scope. (0x0F)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of Javascript code with scope.
   */
  final val CodeWithScope: Byte = 0x0F

  /**
   * Get the type for a 32bit integer. (0x10)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of the 32 bit integer.
   */
  final val Int32: Byte = 0x10

  /**
   * Get the type for a 64bit integer. (0x12)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of the 64 bit integer.
   */
  final val Int64: Byte = 0x12

  /**
   * A hash of the mappings from a single byte to the wrapper class to be
   * used in BSON loading.
   *
   * @return The mappings.
   */
  final val mappings = HashMap[Byte, Readable](
    Boolean  -> companion[BsonBoolean.type],
    Double   -> companion[BsonDouble.type],
    String   -> companion[BsonString.type],
    Int32    -> companion[BsonInt.type],
    Int64    -> companion[BsonLong.type],
    ObjectId -> companion[org.scooter.bson.ObjectId.type]
  )
}
