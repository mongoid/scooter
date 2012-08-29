package org.scooter.protocol

import io.netty.buffer.ByteBuf

import org.scooter.Collection
import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._

/**
 * Companion object for the Insert class.
 */
object Update {

  /**
   * Instantiate the new Update.
   *
   * @param collection The Collection to update documents in.
   * @param selector The selector Document.
   * @param update The update Document.
   *
   * @return The Update command.
   */
  def apply(collection: Collection, selector: Document, update: Document) = {
    new Update(Header(0, 0, 2001), collection.fullName, selector, update)
  }
}

/**
 * This class encapsulates behaviour for the OP_UPDATE command in the
 * MongoDB Wire Protocol.
 *
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param header The Header.
 * @param name The full name of the Collection.
 * @param selector The selector Document.
 * @param update The update Document.
 */
sealed case class Update(
  header: Header, name: String, selector: Document, update: Document
) extends Command(header, 2001) {

  /**
   * Serialize the Update into a buffer that can be written to the socket.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @note The order in which bytes must be placed into the buffer:
   * - The standard header.
   * - A 4 byte placeholder.
   * - The full name of the collection.
   * - The bit vector of flags.
   * - The selector Document.
   * - The update Document.
   * - Replace the length at position zero after everything is written.
   *
   * @param buffer The ByteBuf that will get written.
   */
  def encode(buffer: ByteBuf) = {
    withHeader(buffer) {
      buffer.writeInt(0) // Placeholder.
      buffer.writeCString(name)
      buffer.writeInt(0) // Bit vector.
      selector.encode(buffer)
      update.encode(buffer)
    }
  }
}
