package org.mongoid.scooter.bson

import language.implicitConversions

/**
 * Contains the implicit conversions for all BSON types to their wrappers.
 */
object Conversions {

  /**
   * Implicit conversion from a Boolean to a BooleanWrapper.
   *
   * @param target The Boolean that is getting wrapped.
   *
   * @return The BooleanWrapper around the Boolean.
   */
  implicit def wrapBoolean(target: Boolean) = new BooleanWrapper(target)

  /**
   * Implicit conversion from a Int to a IntWrapper.
   *
   * @param target The Int that is getting wrapped.
   *
   * @return The IntWrapper around the Int.
   */
  implicit def wrapInt(target: Int) = new IntWrapper(target)

  /**
   * Implicit conversion from a Float to a FloatWrapper.
   *
   * @param target The Float that is getting wrapped.
   *
   * @return The FloatWrapper around the Float.
   */
  implicit def wrapFloat(target: Float) = new FloatWrapper(target)

  /**
   * Implicit conversion from a Long to a LongWrapper.
   *
   * @param target The Long that is getting wrapped.
   *
   * @return The LongWrapper around the Long.
   */
  implicit def wrapLong(target: Long) = new LongWrapper(target)

  /**
   * Implicit conversion from a String to a StringWrapper.
   *
   * @param target The String that is getting wrapped.
   *
   * @return The StringWrapper around the String.
   */
  implicit def wrapString(target: String) = new StringWrapper(target)
}
