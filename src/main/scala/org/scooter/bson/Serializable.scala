package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer

trait Serializable {

  /**
   * Dump the value to the buffer in it's proper BSON format.
   *
   * @param buffer The buffer being written to.
   * @param key The string key to this instance string value.
   */
  def bsonDump(buffer: ChannelBuffer, key: String) : Unit
}