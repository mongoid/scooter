package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBufferIndexFinder.NUL

/**
 * Wraps the ChannelBuffer to provide additional read operations with respect
 * to BSON deserialization specifics.
 *
 * @param target The wrapped ChannelBuffer.
 */
class ChannelBufferWrapper(target: ChannelBuffer) {

  /**
   * Read a C String from the buffer.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The bytes that are getting read:
   *  - The bytes up to the next null byte (0x00).
   *  - The null byte itself to increment the read index.
   *
   * @return The String.
   */
  def readCString: String = {
    return readString(target.bytesBefore(NUL))
  }

  /**
   * Read a String from the buffer.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The bytes that are getting read:
   *  - The integer bytes for the length of the string.
   *  - The bytes for the string for the specified length.
   *  - The null byte that follows the end of the string.
   *
   * @return The String.
   */
  def readString: String = {
    return readString(target.readInt - 1)
  }

  /**
   * Read the bytes for a String of the specified length plus the trailing
   * null byte and return the String.
   *
   * @param length The length of the String.
   *
   * @return The String.
   */
  private def readString(length: Int): String = {
    val buffer = target.readBytes(length)
    target.readByte
    return new String(buffer.array)
  }
}
