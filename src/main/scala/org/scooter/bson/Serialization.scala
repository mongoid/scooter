package org.scooter.bson

import language.implicitConversions

/**
 * Contains the implicit conversions for all BSON types to their wrappers.
 */
object Serialization {

  /**
   * Implicit conversion from a Double to a DoubleWrapper.
   *
   * @param target The Double that is getting wrapped.
   *
   * @return The DoubleWrapper around the Double.
   */
  implicit def bsonSerializable(target: Double): Serializable = {
    new DoubleWrapper(target)
  }

  /**
   * Implicit conversion from a Boolean to a BooleanWrapper.
   *
   * @param target The Boolean that is getting wrapped.
   *
   * @return The BooleanWrapper around the Boolean.
   */
  implicit def bsonSerializable(target: Boolean): Serializable = {
    new BooleanWrapper(target)
  }

  /**
   * Implicit conversion from a Int to a IntWrapper.
   *
   * @param target The Int that is getting wrapped.
   *
   * @return The IntWrapper around the Int.
   */
  implicit def bsonSerializable(target: Int): Serializable = {
    new IntWrapper(target)
  }

  /**
   * Implicit conversion from a Float to a FloatWrapper.
   *
   * @param target The Float that is getting wrapped.
   *
   * @return The FloatWrapper around the Float.
   */
  implicit def bsonSerializable(target: Float): Serializable = {
    new FloatWrapper(target)
  }

  /**
   * Implicit conversion from a Long to a LongWrapper.
   *
   * @param target The Long that is getting wrapped.
   *
   * @return The LongWrapper around the Long.
   */
  implicit def bsonSerializable(target: Long): Serializable = {
    new LongWrapper(target)
  }

  /**
   * Implicit conversion from a String to a StringWrapper.
   *
   * @param target The String that is getting wrapped.
   *
   * @return The StringWrapper around the String.
   */
  implicit def bsonSerializable(target: String): Serializable = {
    new StringWrapper(target)
  }
}
