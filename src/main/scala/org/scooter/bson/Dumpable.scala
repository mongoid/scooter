package org.scooter.bson

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

trait Dumpable {

  /**
   * Dump the value to the buffer in it's proper BSON format.
   *
   * @param buffer The buffer being written to.
   * @param key The string key to this instance string value.
   */
  def bsonDump(buffer: Buffer, key: String) : Unit
}
