package org.scooter.bson

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._

import scala.collection.mutable.HashMap

/**
 * Companion object for a bson Document.
 */
object Document extends Deserializable {

  /**
   * Instantiate a new document from the provided pairs.
   *
   * @param elements The key -> pair elements.
   *
   * @return The Document.
   */
  def apply(elements: (String, Any)*): Document = {
    val document = new Document
    elements.foreach{ case (key, value) => document(key) = value }
    return document
  }

  /**
   * Loads a Document from the buffer.
   *
   * @param buffer The ChannelBuffer.
   *
   * @return Map The document as a map.
   */
  def bsonLoad(buffer: ChannelBuffer, doc: Document) = {
  }
}

/**
 * A wrapper for a hash that is a representation of the document
 * as BSON.
 *
 * @param document The Hash to wrap.
 */
class Document extends HashMap[String, Any] {

  /**
   * Dump the document to the buffer, and yield to the provided
   * function.
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The length of the document in bytes.
   *  - The document.
   *  - A null byte.
   *
   * @param buffer The ChannelBuffer to write to.
   * @param func The function to apply to each pair in the hash.
   */
  def bsonDump(buffer: ChannelBuffer) = {
    val start = buffer.writerIndex
    buffer.writeInt(0)
    serializePairs
    buffer.writeZero(1)
    buffer.setInt(start, buffer.writerIndex - start)

    /**
     * Serialize each key/value pair into the ChannelBuffer.
     *
     * @todo: Can we get implicit conversions here?
     */
    def serializePairs = {
      foreach {
        case (key, value: Boolean) => value.bsonDump(buffer, key)
        case (key, value: Double)  => value.bsonDump(buffer, key)
        case (key, value: Float)   => value.bsonDump(buffer, key)
        case (key, value: Int)     => value.bsonDump(buffer, key)
        case (key, value: String)  => value.bsonDump(buffer, key)
      }
    }
  }
}
