package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer

trait Deserializable {

  /**
   * Dump the value to the buffer in it's proper BSON format.
   *
   * @param buffer The buffer being read from.
   * @param doc The document to write to.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: Document) : Unit
}
