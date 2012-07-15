package org.mongoid.scooter.protocol

import java.nio.ByteBuffer

import org.mongoid.scooter.Collection
import org.mongoid.scooter.bson._
import org.mongoid.scooter.bson.Conversions._

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
  def apply(collection: Collection, documents: Array[_<:Map[String, Any]]) = {
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
class Insert(name: String, documents: Array[_<:Map[String, Any]]) extends Message {

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
   *
   * @param buffer The ByteBuffer that will get written.
   */
  def serialize(buffer: ByteBuffer) = {
    serializeHeader(buffer)
    buffer.putInt(0)
    buffer.put(name.getBytes)
    serializeDocuments(buffer)
  }

  /**
   * Serialize the documents to the ByteBuffer.
   *
   * @param buffer The ByteBuffer that will get written.
   */
  private def serializeDocuments(buffer: ByteBuffer) = {
    documents.foreach {
      doc => new Document(doc).bsonDump(buffer) {
        case (key: String, value: String) => value.bsonDump(buffer, key)
      }
    }
  }
}
