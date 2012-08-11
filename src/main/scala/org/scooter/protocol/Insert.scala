package org.scooter.protocol

import org.jboss.netty.buffer.ChannelBuffer

import org.scooter.Collection
import org.scooter.bson._
import org.scooter.bson.implicits.BsonChannelBuffer._
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
sealed case class Insert(name: String, documents: Seq[Document])
  extends Message(2002) {

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
    header.serialize(buffer)
    buffer.writeInt(0) // Bit vector.
    buffer.writeCString(name)
    documents.foreach(doc => doc.bsonDump(buffer))
    buffer.setInt(0, buffer.writerIndex)
  }
}
