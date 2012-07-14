package org.mongoid.scooter.bson

import language.implicitConversions

/**
 * Companion object for the StringWrapper class.
 */
object Conversions {

  /**
   * Implicit conversion from a String to a StringWrapper.
   *
   * @param target The String that is getting wrapped.
   * @return The StringWrapper around the String.
   */
  implicit def wrapString(target: String) = new StringWrapper(target)
}
