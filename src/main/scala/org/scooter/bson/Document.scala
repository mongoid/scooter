package org.scooter.bson

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._
import org.scooter.functional.Utilities._

import scala.collection.mutable.HashMap

/**
 * Companion object for a bson Document.
 */
object Document {

  /**
   * Instantiate a new document from the provided pairs.
   *
   * @param elements The key -> pair elements.
   *
   * @return The Document.
   */
  def apply(elements: (String, Dumpable)*) = {
    elements.foldLeft(new Document)(
      (doc, pair) => doc += (pair._1 -> pair._2)
    )
  }

  /**
   * Loads a Document from the buffer.
   *
   * @param buffer The ChannelBuffer.
   *
   * @return The document.
   */
  def bsonLoad(buffer: Buffer) = {
    new Document tap(
      doc => {
        val length = buffer.readInt
        loadPair(buffer, buffer.readByte, doc)
      }
    )
  }

  /**
   * Recursive function to load all the key/value pairs that
   * are in the buffer.
   *
   * @note This function operates by:
   *   - Look at the provided byte to get the type of object.
   *   - Get the Loadable for that type, and load the bytes.
   *   - Read the next byte.
   *
   * @param buffer The ChannelBuffer to read from.
   * @param byte The Byte representing the value type or zero.
   * @param doc The Document being written into.
   */
  private def loadPair(buffer: Buffer, byte: Byte, doc: Document): Unit = {
    if (byte != Bytes.Null) {
      Bytes.getCompanion(byte).bsonLoad(buffer, doc)
      loadPair(buffer, buffer.readByte, doc)
    }
  }
}

/**
 * A wrapper for a hash that is a representation of the document
 * as BSON.
 *
 * @param document The Hash to wrap.
 */
class Document extends HashMap[String, Dumpable] {

  /**
   * Dump the document to the buffer, and yield to the provided
   * function.
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The length of the document in bytes.
   *  - The document.
   *  - A null byte.
   *  - The length of the entire message.
   *
   * @param buffer The ChannelBuffer to write to.
   */
  def bsonDump(buffer: Buffer) = {
    val start = buffer.writerIndex
    buffer.writeInt(0)
    foreach(pair => pair._2.bsonDump(buffer, pair._1))
    buffer.writeZero(1)
    buffer.setInt(start, buffer.writerIndex - start)
  }
}
