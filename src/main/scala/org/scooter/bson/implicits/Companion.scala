package org.scooter.bson.implicits

import scala.reflect.ClassTag

/**
 * Provides utility methods for getting companion singletons.
 */
object Companion {

  /**
   * Get the singleton companion object for the provided type.
   *
   * @return The singleton companion object.
   */
  def singleton[T](implicit tag: ClassTag[T]) = {
    val name = tag.runtimeClass.getName()
    val klass = Class.forName(name)
    klass.getField("MODULE$").get(klass).asInstanceOf[T]
  }
}
