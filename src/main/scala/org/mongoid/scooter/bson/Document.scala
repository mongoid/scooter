package org.mongoid.scooter.bson

/**
 * A wrapper for a hash that is a representation of the document
 * as BSON.
 *
 * @param document The Hash to wrap.
 */
class Document(document: Map[String, Any]) {

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