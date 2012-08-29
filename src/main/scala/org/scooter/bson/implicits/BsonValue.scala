package org.scooter.bson.implicits

import org.scooter.bson.Writable

/**
 * Values that can be converted to BSON can be implicitly converted to
 * classes that extend from this object.
 *
 * @param target The wrapped convertable class.
 */
abstract class BsonValue[+T](target: T) extends Writable {

  /**
   * For convenience, delegate the toString method to the target for
   * more sane output.
   *
   * @return The target as a String.
   */
  override def toString = target.toString
}
