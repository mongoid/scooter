package org.scooter.bson.implicits

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

import org.scooter.bson.{ Bytes, Loadable, Document, Dumpable }
import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._

/**
 * Companion object to the BsonDouble class.
 */
object BsonDouble extends Loadable {

  /**
   * Load the Double value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: Buffer, doc: Document) = {
    doc(buffer.readCString) = buffer.readDouble
  }
}

/**
 * Wraps doubles to provide additional behaviour around BSON serialization.
 *
 * @param target The Double that is wrapped.
 */
case class BsonDouble(target: Double) extends Dumpable {

  /**
   * Dump the double to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
   * @note The order in which bytes must be placed doubleo the buffer:
   *  - The double type.
   *  - The bytes for the double's key.
   *  - A null byte.
   *  - The double.
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance double value.
   */
  def bsonDump(buffer: Buffer, key: String) = {
    buffer.writeByte(Bytes.Double)
    buffer.writeCString(key)
    buffer.writeDouble(target)
  }
}
