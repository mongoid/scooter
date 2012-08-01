package org.scooter.bson

import scala.collection.mutable.HashMap

/**
 * Companion object for a bson Document.
 */
object Document {

  /**
   * Loads a Document from the buffer.
   *
   * @param buffer The Mutable buffer.
   *
   * @return Map The document as a map.
   */
  def bsonLoad[T<:Any](buffer: MutableBuffer, doc: HashMap[String, T]) = {
    // io.read 4
    // while (buf = io.readbyte) != 0
      // key = io.gets(NULL_BYTE).from_utf8_binary.chop!
      // if native_class = Types::MAP[buf]
        // doc[key] = native_class.__bson_load__(io)
      // end
    // end
    // while(bytes = buffer.
  }
}

/**
 * A wrapper for a hash that is a representation of the document
 * as BSON.
 *
 * @param document The Hash to wrap.
 */
class Document[T<:Any](document: HashMap[String, T]) {

  /**
   * Dump the document to the buffer, and yield to the provided
   * function.
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The length of the document in bytes.
   *  - The document.
   *  - A null byte.
   *
   * @param buffer The MutableBuffer to write to.
   * @param func The function to apply to each pair in the hash.
   */
  def bsonDump(buffer: MutableBuffer)(func: Any => Unit) = {
    val start = buffer.position
    buffer.putInt(0)
    document.foreach(func)
    buffer.putByte(Bytes.NULL)
    buffer.putInt(start, buffer.position - start)
  }
}
