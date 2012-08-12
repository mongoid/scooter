package org.scooter.protocol

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

import org.scooter.Collection
import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Document

/**
 * Companion object for the Query class.
 */
object Query {

  /**
   * Instantiate a new Query message.
   *
   * @param collection The Collection to insert into.
   * @param selector The selector Document.
   *
   * @return The Query message.
   */
  def apply(collection: Collection, selector: Document) = {
    new Query(Header(0, 0, 2004), collection.fullName, selector)
  }
}

/**
 * This class encapsulates behaviour for the OP_QUERY command in the
 * MongoDB Wire Protocol.
 *
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param name The full name of the Collection.
 * @param selector The selector Document.
 */
sealed case class Query(head: Header, name: String, selector: Document)
  extends Request(head, 2004) {

  /**
   * Serialize the Query into a buffer that can be written to the socket.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The standard header.
   *  - The bit vector of flags.
   *  - The full name of the collection.
   *  - The number of documents to skip. (int)
   *  - The number of documents to return. (int)
   *  - The selector document.
   *  - The optional return fields document.
   *
   * @param buffer The ChannelBuffer that will get written.
   */
  def serialize(buffer: Buffer) = {
    withHeader(buffer) {
      buffer.writeInt(0) // Bit vector.
      buffer.writeCString(name)
      buffer.writeInt(0) // Skip.
      buffer.writeInt(0) // Return.
      selector.bsonDump(buffer)
    }
  }
}
