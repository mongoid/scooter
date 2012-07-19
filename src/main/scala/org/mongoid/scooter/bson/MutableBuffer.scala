package org.mongoid.scooter.bson

import java.nio.{ ByteBuffer, ByteOrder, BufferOverflowException }
import language.implicitConversions

/**
 * Companion object to the MutableBuffer.
 */
object MutableBuffer {

  /**
   * Instantiate a new MutableBuffer.
   *
   * @param factor The factor at which the buffer should expand.
   *
   * @return The MutableBuffer.
   */
  def apply(factor: Int) = {
    val buffer = ByteBuffer.allocate(factor).order(ByteOrder.LITTLE_ENDIAN)
    new MutableBuffer(factor, buffer)
  }

  /**
   * Methods not defined on the MutableBuffer should delegate through
   * to the wrapped ByteBuffer.
   *
   * @param buffer The MutableBuffer.
   *
   * @return The wrapped ByteBuffer.
   */
  implicit def delegateToBuffer(buffer: MutableBuffer) = buffer.byteBuffer
}

/**
 * Represents a buffer of Bytes that can be expanded as the buffer
 * capacity is exhausted.
 *
 * @param factor The factor at which to expand the buffer.
 * @param buffer The wrapped ByteBuffer.
 */
class MutableBuffer(factor: Int, var buffer: ByteBuffer) {

  /**
   * Convenience method for getting the wrapped ByteBuffer.
   *
   * @return The ByteBuffer.
   */
  def byteBuffer = buffer

  /**
   * Put a byte into the buffer. Will expand the size if necessary.
   *
   * @param value The Byte to insert.
   *
   * @return This MutableBuffer.
   */
  def putByte(value: Byte) = {
    putValue(buffer => buffer.put(value))
  }

  /**
   * Put an Int into the buffer. Will expand the size if necessary.
   *
   * @param value The Int to insert.
   *
   * @return This MutableBuffer.
   */
  def putInt(value: Int) = {
    putValue(buffer => buffer.putInt(value))
  }

  /**
   * Put an Long into the buffer. Will expand the size if necessary.
   *
   * @param value The Long to insert.
   *
   * @return This MutableBuffer.
   */
  def putLong(value: Long) = {
    putValue(buffer => buffer.putLong(value))
  }

  /**
   * Put a String into the buffer. Will expand the size if necessary.
   *
   * @param value The String to insert.
   *
   * @return This MutableBuffer.
   */
  def putString(value: String) = {
    putValue(buffer => buffer.put(value.getBytes))
  }

  /**
   * Put a value into the buffer, and expand if the buffer was exhausted.
   *
   * @api private
   *
   * @param func The function to yield to.
   *
   * @return This MutableBuffer.
   */
  private def putValue(func: ByteBuffer => ByteBuffer) = {
    try {
      func(buffer)
    }
    catch {
      case overflow: BufferOverflowException => expandBuffer(func)
    }
    this
  }

  /**
   * Expand the buffer by creating a new one with this buffer's contents
   * and an increased capacity by the factor.
   *
   * @api private
   *
   * @param func The function to yield to after expansion.
   *
   * @return ByteBuffer The result of the function call.
   */
  private def expandBuffer(func: ByteBuffer => ByteBuffer) = {
    val array = buffer.array ++ new Array[Byte](factor)
    val position = buffer.position
    buffer = ByteBuffer.wrap(array).order(ByteOrder.LITTLE_ENDIAN)
    buffer.position(position)
    func(buffer)
  }
}
