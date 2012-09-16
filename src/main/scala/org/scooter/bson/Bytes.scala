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
  final val Null: Byte = 0

  /**
   * Get the type for a Double. (0x01)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a Double.
   */
  final val Double: Byte = 1

  /**
   * Get the type for a String. (0x02)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a UTF-8 string.
   */
  final val String: Byte = 2

  /**
   * Get the type for an embedded document. (0x03)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of an embedded document.
   */
  final val Embedded: Byte = 3

  /**
   * Get the type for an Array. (0x04)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of an Array.
   */
  final val Array: Byte = 4

  /**
   * Get the type for a Binary. (0x05)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a binary.
   */
  final val Binary: Byte = 5

  /**
   * Get the type for an Object Id. (0x07)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of an object id.
   */
  final val ObjectId: Byte = 7

  /**
   * Get the type for a bolean. (0x08)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a boolean.
   */
  final val Boolean: Byte = 8

  /**
   * Get the type for a UTC time. (0x09)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a UTC time.
   */
  final val Time: Byte = 9

  /**
   * Get the type for null value. (0x0A)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a null.
   */
  final val NullValue: Byte = 10

  /**
   * Get the type for a regular expression. (0x0B)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a regular expression.
   */
  final val Regex: Byte = 11

  /**
   * Get the type for Javascript code. (0x0D)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of Javascript code.
   */
  final val Code: Byte = 13

  /**
   * Get the type for a Symbol. (0x0E)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of a Symbol.
   */
  final val Symbol: Byte = 14

  /**
   * Get the type for Javascript code with scope. (0x0F)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of Javascript code with scope.
   */
  final val CodeWithScope: Byte = 15

  /**
   * Get the type for a 32bit integer. (0x10)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of the 32 bit integer.
   */
  final val Int32: Byte = 16

  /**
   * Get the type for a 64bit integer. (0x12)
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of the 64 bit integer.
   */
  final val Int64: Byte = 18

  /**
   * Get the type for the minimum value possible.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of the min key.
   */
  final val MinKey: Byte = -1

  /**
   * Get the type for the maximum value possible.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @return The byte representation of the max key.
   */
  final val MaxKey: Byte = 127

  /**
   * A hash of the mappings from a single byte to the wrapper class to be
   * used in BSON loading.
   *
   * @return The mappings.
   *
   * @todo We can do better than this.
   */
  final val mappings = HashMap[Byte, Readable](
    Boolean  -> companion[BsonBoolean.type],
    Double   -> companion[BsonDouble.type],
    String   -> companion[BsonString.type],
    Embedded -> companion[Document.type],
    Int32    -> companion[BsonInt.type],
    Int64    -> companion[BsonLong.type],
    ObjectId -> companion[org.scooter.bson.ObjectId.type],
    MinKey   -> companion[org.scooter.bson.MinKey.type],
    MaxKey   -> companion[org.scooter.bson.MaxKey.type]
  )
}
