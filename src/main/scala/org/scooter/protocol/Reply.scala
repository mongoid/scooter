package org.scooter.protocol

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonChannelBuffer._

/**
 * Companion object for Reply.
 */
object Reply extends Deserializable {

  /**
   * Deserialize the buffer into a Reply object.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @note The order in which bytes must be read from the buffer:
   *  - The standard header.
   *  - The query flags.
   *  - The id of the cursor on the server.
   *  - The marker where the documents start from in the cursor.
   *  - The number of documents returned.
   *  - The documents.
   *
   * @param buffer The ChannelBuffer that is the exact frame of the response.
   *
   * @return The Reply.
   */
  def deserialize(buffer: Buffer): Reply = {
    new Reply(
      buffer.readHeader,
      buffer.readInt,
      buffer.readLong,
      buffer.readInt,
      documents(buffer)
    )
  }

  /**
   * Deserialize a sequence of Documents from the buffer.
   *
   * @param buffer The ChannelBuffer that has only documents to read.
   *
   * @return A sequence of Documents.
   */
  private def documents(buffer: Buffer) = {
    (1 to buffer.readInt).foldLeft(List[Document]())(
      (docs, bytes) => docs.:+(Document.bsonLoad(buffer))
    )
  }
}

/**
 * Represents a Reply message from the database.
 *
 * @param header The message Header.
 * @param flags The options.
 * @param cursor The id of the cursor if there are more documents to read.
 * @param skip The marker of where the cursor is on the server.
 * @param docs The sequence of Documents in this batch.
 */
sealed case class Reply(
  override val header: Header,
  val flags: Int,
  val cursor: Long,
  val skip: Int,
  val documents: Seq[Document]) extends Message(1)
