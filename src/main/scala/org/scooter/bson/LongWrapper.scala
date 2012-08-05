package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.ChannelBufferWrapper._
import org.scooter.bson.Serialization._

/**
 * Wraps ints to provide additional behaviour around BSON serialization.
 *
 * @param target The Long that is wrapped.
 */
class LongWrapper(target: Long) extends Serializable {

  /**
   * Dump the int to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The long type.
   *  - The bytes for the long's key.
   *  - A null byte.
   *  - The long.
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance int value.
   */
  def bsonDump(buffer: ChannelBuffer, key: String) = {
    buffer.writeByte(Bytes.Int64)
    buffer.writeCString(key)
    buffer.writeLong(target)
  }
}
