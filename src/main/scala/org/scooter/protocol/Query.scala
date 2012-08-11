package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.bson.Document

/**
 * This class encapsulates behaviour for the OP_QUERY command in the
 * MongoDB Wire Protocol.
 *
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param name The full name of the Collection.
 * @param selector The selector Document.
 */
sealed case class Query(name: String, selector: Document)
  extends Message(2004) {

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
  def serialize(buffer: ChannelBuffer) = {
  }
}
