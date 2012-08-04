package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer
import scala.collection.mutable.Map

trait Deserializable {

  /**
   * Dump the value to the buffer in it's proper BSON format.
   *
   * @param buffer The buffer being written to.
   * @param key The string key to this instance string value.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: Map[String, Any]) : Unit
}
