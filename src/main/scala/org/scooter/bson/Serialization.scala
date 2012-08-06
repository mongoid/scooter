package org.scooter.bson

import language.implicitConversions

import org.scooter.bson.implicits._

/**
 * Contains the implicit conversions for all BSON types to their wrappers.
 */
object Serialization {

  /**
   * Implicit conversion from a Double to a Double.
   *
   * @param target The Double that is getting wrapped.
   *
   * @return The Double around the Double.
   */
  implicit def serializable(target: Double): Serializable = {
    new BsonDouble(target)
  }

  /**
   * Implicit conversion from a Boolean to a Boolean.
   *
   * @param target The Boolean that is getting wrapped.
   *
   * @return The Boolean around the Boolean.
   */
  implicit def serializable(target: Boolean): Serializable = {
    new BsonBoolean(target)
  }

  /**
   * Implicit conversion from a Int to a Int.
   *
   * @param target The Int that is getting wrapped.
   *
   * @return The Int around the Int.
   */
  implicit def serializable(target: Int): Serializable = {
    new BsonInt(target)
  }

  /**
   * Implicit conversion from a Long to a Long.
   *
   * @param target The Long that is getting wrapped.
   *
   * @return The Long around the Long.
   */
  implicit def serializable(target: Long): Serializable = {
    new BsonLong(target)
  }

  /**
   * Implicit conversion from a String to a String.
   *
   * @param target The String that is getting wrapped.
   *
   * @return The String around the String.
   */
  implicit def serializable(target: String): Serializable = {
    new BsonString(target)
  }
}
