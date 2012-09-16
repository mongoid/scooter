package org.scooter.bson

import io.netty.buffer.ByteBuf

import org.scooter.bson.implicits.BsonByteBuf._

/**
 * Companion object to the MaxKey class.
 */
object MaxKey extends Readable {

  /**
   * Load the MaxKey value and its key from the buffer.
   *
   * @param buffer The ByteBuf.
   * @param doc The document to place in.
   */
  def read(buffer: ByteBuf, doc: Document) = {
    doc(buffer.readCString) = new MaxKey
  }
}

/**
 * Represents the maximum value for comparison in the bson specification.
 */
case class MaxKey() extends Writable {

  /**
   * Dump the MaxKey to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
    * @note The order in which bytes must be placed in the buffer:
    * - The single byte value for the max key.
    * - The name of the field
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance value.
   */
  def write(buffer: ByteBuf, key: String) = {
    buffer.writeByte(Bytes.MaxKey)
    buffer.writeCString(key)
  }
}
