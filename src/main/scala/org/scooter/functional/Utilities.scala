package org.scooter.functional

import scala.language.implicitConversions
import scala.reflect.ClassTag

/**
 * Provides general utility functions for use in the library.
 */
object Utilities {

  /**
   * Get the companion object for the provided type.
   *
   * @param tag The implicit class tag for the type.
   *
   * @return The companion object.
   */
  def companion[T](implicit tag: ClassTag[T]) = {
    val name = tag.runtimeClass.getName()
    val klass = Class.forName(name)
    klass.getField("MODULE$").get(klass).asInstanceOf[T]
  }

  /**
   * Wrapper for performing K Combinator operations, like Ruby's tap.
   *
   * @param value The wrapped value.
   */
  class Tap[T](value: T) {

    /**
     * Execute the provided function, yielding the wrapped value and
     * return the wrapped value.
     *
     * @param func The function to call.
     *
     * @return The wrapped value.
     */
    def tap(func: (T) => Unit): T = {
      func(value)
      value
    }
  }

  /**
   * Wrap any object in a Tap (K Combinator).
   *
   * @param value The value to wrap.
   *
   * @return Tap The wrapped value.
   */
  implicit def wrapTap[T](value: T) = new Tap[T](value)
}
