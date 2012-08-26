package org.scooter.protocol

import io.netty.buffer.ByteBuf

import org.scooter.Collection
import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._

/**
 * Companion object for the Delete class.
 */
object Delete {

  /**
   * Instantiate a new Delete message.
   *
   * @param collection The Collection to insert into.
   * @param selector The selector Document.
   *
   * @return The Delete message.
   */
  def apply(collection: Collection, selector: Document) = {
    new Delete(Header(0, 0, 2006), collection.fullName, selector)
  }
}

/**
 * This class encapsulates behaviour for the OP_DELETE command in the
 * MongoDB Wire Protocol.
 *
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param header The Header.
 * @param name The full name of the Collection.
 * @param selector The document selector.
 */
sealed case class Delete (header: Header, name: String, document: Document)
  extends Command(header, 2006) {

  /**
   * Serialize the Delete into a buffer that can be written to the socket.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The standard header.
   *  - A placeholder zero int32.
   *  - The full name of the collection.
   *  - A bit vector of flags.
   *  - The selector document.
   *  - Replace the length at position zero after everything is written.
   *
   * @param buffer The ByteBuf that will get written.
   */
  def serialize(buffer: ByteBuf) = {
    withHeader(buffer) {
      buffer.writeInt(0) // Placeholder.
      buffer.writeCString(name)
      buffer.writeInt(0) // Bit vector.
      document.bsonWrite(buffer)
    }
  }
}
