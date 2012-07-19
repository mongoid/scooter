package org.mongoid.scooter.bson

import language.implicitConversions

/**
 * Companion object for the StringWrapper class.
 */
object Conversions {

  /**
   * Implicit conversion from a Int to a IntWrapper.
   *
   * @param target The Int that is getting wrapped.
   *
   * @return The IntWrapper around the Int.
   */
  implicit def wrapInt(target: Int) = new IntWrapper(target)

  /**
   * Implicit conversion from a String to a StringWrapper.
   *
   * @param target The String that is getting wrapped.
   *
   * @return The StringWrapper around the String.
   */
  implicit def wrapString(target: String) = new StringWrapper(target)
}
