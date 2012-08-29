package org.scooter.bson

import io.netty.buffer.ByteBuf

import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._
import org.scooter.codec.{ Decodable, Encodable }
import org.scooter.functional.Utilities._

import scala.collection.mutable.HashMap

/**
 * Companion object for a bson Document.
 */
object Document extends Decodable[Document] with Readable {

  /**
   * Instantiate a new document from the provided pairs.
   *
   * @param elements The key -> pair elements.
   *
   * @return The Document.
   */
  def apply(elements: (String, Writable)*) = {
    elements.foldLeft(new Document)(
      (doc, pair) => doc += (pair._1 -> pair._2)
    )
  }

  /**
   * Decode the Document from the ByteBuf.
   *
   * @param buffer The ByteBuf to decode.
   *
   * @return The decoded Document.
   */
  def decode(buffer: ByteBuf) = {
    readDocument(buffer)
  }

  /**
   * Read the embedded document from the buffer and set it with the key
   * int the provided Document.
   *
   * @param buffer The ByteBuf.
   * @param doc The document to put the embedded document in.
   */
  def read(buffer: ByteBuf, doc: Document) = {
    doc(buffer.readCString) = readDocument(buffer)
  }

  /**
   * Reads a Document from the buffer.
   *
   * @param buffer The ByteBuf.
   *
   * @return The document.
   */
  private def readDocument(buffer: ByteBuf) = {
    (new Document).tap {
      doc => {
        val length = buffer.readInt
        loadPair(buffer, buffer.readByte, doc)
      }
    }
  }

  /**
   * Recursive function to load all the key/value pairs that
   * are in the buffer.
   *
   * @note This function operates by:
   *   - Look at the provided byte to get the type of object.
   *   - Get the Readable for that type, and load the bytes.
   *   - Read the next byte.
   *
   * @param buffer The ByteBuf to read from.
   * @param byte The Byte representing the value type or zero.
   * @param doc The Document being written into.
   */
  private def loadPair(buffer: ByteBuf, byte: Byte, doc: Document): Unit = {
    if (byte != Bytes.Null) {
      Bytes.getCompanion(byte).read(buffer, doc)
      loadPair(buffer, buffer.readByte, doc)
    }
  }
}

/**
 * A wrapper for a hash that is a representation of the document
 * as BSON.
 *
 * @param document The Hash to wrap.
 */
class Document extends HashMap[String, Writable] with Encodable with Writable {

  /**
   * Encode the document.
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The length of the document in bytes.
   *  - The document.
   *  - A null byte.
   *  - The length of the entire message.
   *
   * @param buffer The ByteBuf to write to.
   */
  def encode(buffer: ByteBuf) = buffer.writeDocument(this)

  /**
   * Write the embedded document to the buffer for the provided key.
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The document type.
   *  - The bytes for the documents's key.
   *  - A null byte.
   *  - The length of the document in bytes.
   *  - The document.
   *  - A null byte.
   *  - The length of the entire message.
   *
   * @param buffer The ByteBuf to write to.
   */
  def write(buffer: ByteBuf, key: String) = {
    buffer.writeByte(Bytes.Embedded)
    buffer.writeCString(key)
    buffer.writeDocument(this)
  }
}
