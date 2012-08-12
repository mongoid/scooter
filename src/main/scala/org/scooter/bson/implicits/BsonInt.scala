package org.scooter.bson.implicits

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

import org.scooter.bson.{ Bytes, Loadable, Document, Dumpable }
import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BsonInt class.
 */
object BsonInt extends Loadable {

  /**
   * Load the Int value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: Buffer, doc: Document) = {
    doc(buffer.readCString) = buffer.readInt
  }
}

/**
 * Wraps ints to provide additional behaviour around BSON serialization.
 *
 * @param target The Int that is wrapped.
 */
case class BsonInt(target: Int) extends Dumpable {

  /**
   * Dump the int to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The int type.
   *  - The bytes for the int's key.
   *  - A null byte.
   *  - The int.
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance int value.
   */
  def bsonDump(buffer: Buffer, key: String) = {
    buffer.writeByte(Bytes.Int32)
    buffer.writeCString(key)
    buffer.writeInt(target)
  }
}
