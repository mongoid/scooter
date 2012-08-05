package org.scooter.bson

import language.implicitConversions

import org.jboss.netty.buffer.ChannelBuffer

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
  implicit def serializableBoolean(target: Boolean): Serializable = {
    new BooleanWrapper(target)
  }

  /**
   * Implicit conversion from a ChannelBuffer to a ChannelBufferWrapper.
   *
   * @param target The ChannelBuffer that is getting wrapped.
   *
   * @return The ChannelBufferWrapper.
   */
  implicit def serializableBuffer(target: ChannelBuffer): ChannelBufferWrapper = {
    new ChannelBufferWrapper(target)
  }

  /**
   * Implicit conversion from a Int to a IntWrapper.
   *
   * @param target The Int that is getting wrapped.
   *
   * @return The IntWrapper around the Int.
   */
  implicit def serializableInt(target: Int): Serializable = {
    new IntWrapper(target)
  }

  /**
   * Implicit conversion from a Float to a FloatWrapper.
   *
   * @param target The Float that is getting wrapped.
   *
   * @return The FloatWrapper around the Float.
   */
  implicit def serializableFloat(target: Float): Serializable = {
    new FloatWrapper(target)
  }

  /**
   * Implicit conversion from a Long to a LongWrapper.
   *
   * @param target The Long that is getting wrapped.
   *
   * @return The LongWrapper around the Long.
   */
  implicit def serializableLong(target: Long): Serializable = {
    new LongWrapper(target)
  }

  /**
   * Implicit conversion from a String to a StringWrapper.
   *
   * @param target The String that is getting wrapped.
   *
   * @return The StringWrapper around the String.
   */
  implicit def serializableString(target: String): Serializable = {
    new StringWrapper(target)
  }
}
