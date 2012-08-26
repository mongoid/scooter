package org.scooter.protocol

import io.netty.buffer.ByteBuf

import org.scooter.Collection
import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._

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
  def apply(collection: Collection, documents: Seq[Document]) = {
    new Insert(Header(0, 0, 2002), collection.fullName, documents)
  }
}

/**
 * This class encapsulates behaviour for the OP_INSERT command in the
 * MongoDB Wire Protocol.
 *
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param header The Header.
 * @param name The full name of the Collection.
 * @param documents The documents to insert.
 */
sealed case class Insert (
  header: Header,
  name: String,
  documents: Seq[Document]
) extends Command(header, 2002) {

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
   * @param buffer The ByteBuf that will get written.
   */
  def serialize(buffer: ByteBuf) = {
    withHeader(buffer) {
      buffer.writeInt(0) // Bit vector.
      buffer.writeCString(name)
      documents.foreach(doc => doc.bsonWrite(buffer))
    }
  }
}
