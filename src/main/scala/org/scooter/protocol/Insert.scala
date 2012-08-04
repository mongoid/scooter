package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer
import org.scooter.Collection
import org.scooter.bson._
import org.scooter.bson.Conversions._
import scala.collection.mutable.HashMap

/**
 * Companion object for the Insert class.
 */
object Insert {

  /**
   * Instantiate a new Insert message.
   *
   * @param collection The Collection to insert into.
   * @param documents The documents to insert.
   *
   * @return The Insert message.
   */
  def apply(collection: Collection, documents: Array[HashMap[String, Any]]) = {
    new Insert(collection.fullName, documents)
  }
}

/**
 * This class encapsulates behaviour for the OP_INSERT command in the
 * MongoDB Wire Protocol.
 *
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param name The full name of the Collection.
 * @param documents The documents to insert.
 */
class Insert(name: String, documents: Array[HashMap[String, Any]]) extends Message {

  /**
   * Get the operation code for an Insert.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @return The Integer operation code.
   */
  def operationCode = 2002

  /**
   * Serialize the Insert into a buffer that can be written to the socket.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The standard header.
   *  - The bit vector of flags.
   *  - The full name of the collection.
   *  - The documents.
   *  - Replace the length at position zero after everything is written.
   *
   * @param buffer The ChannelBuffer that will get written.
   */
  def serialize(buffer: ChannelBuffer) = {
    serializeHeader(buffer)
    buffer.writeInt(0) // Bit vector.
    buffer.writeBytes(name.getBytes)
    buffer.writeZero(1)
    serializeDocuments(buffer)
    buffer.setInt(0, buffer.writerIndex)
  }

  /**
   * Serialize the documents to the ChannelBuffer.
   *
   * @param buffer The ChannelBuffer that will get written.
   */
  private def serializeDocuments(buffer: ChannelBuffer) = {
    documents.foreach {
      doc => new Document(doc).bsonDump(buffer) {
        case (key: String, value: String) => value.bsonDump(buffer, key)
      }
    }
  }
}