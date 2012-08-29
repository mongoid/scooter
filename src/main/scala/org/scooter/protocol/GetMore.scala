package org.scooter.protocol

import io.netty.buffer.ByteBuf

import org.scooter.Collection
import org.scooter.bson.implicits.BsonByteBuf._

/**
 * Companion to the GetMore class.
 */
object GetMore {

  /**
   * Instantiate a new GetMore request.
   *
   * @param collection The Collection to get more from.
   * @param limit The number of Documents to return.
   * @param cursor The cursor id on the server.
   *
   * @return The GetMore request.
   */
  def apply(collection: Collection, limit: Int, cursor: Long) = {
    new GetMore(Header(0, 0, 2005), collection.fullName, limit, cursor)
  }
}

/**
 * This class encapsulates behaviour for the OP_GETMORE command in the
 * MongoDB Wire Protocol.
 *
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param header The Header.
 * @param name The full name of the Collection.
 * @param limit The number of Documents to return.
 * @param cursor The id of the cursor on the server.
 */
sealed case class GetMore(
  header: Header, name: String, limit: Int, cursor: Long
) extends Request(header, 2005) {

  /**
   * Encode the GetMore into a buffer that can be written to the socket.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The standard header.
   *  - A 4 byte placeholder.
   *  - The full name of the collection.
   *  - The number of documents to return.
   *  - The id fo the cursor on the server.
   *
   * @param buffer The ByteBuf that will get written.
   */
  def encode(buffer: ByteBuf) = {
    withHeader(buffer) {
      buffer.writeInt(0) // Placeholder.
      buffer.writeCString(name)
      buffer.writeInt(limit)
      buffer.writeLong(cursor)
    }
  }
}
