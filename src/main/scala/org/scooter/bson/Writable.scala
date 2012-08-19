package org.scooter.bson

import io.netty.buffer.ByteBuf

trait Writable {

  /**
   * Dump the value to the buffer in it's proper BSON format.
   *
   * @param buffer The buffer being written to.
   * @param key The string key to this instance string value.
   */
  def bsonWrite(buffer: ByteBuf, key: String) : Unit
}
