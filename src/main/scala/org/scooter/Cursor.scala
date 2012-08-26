package org.scooter

import org.scooter.bson.Document

/**
 * Companion object to the Cursor.
 */
object Cursor {

  /**
   * Instantiate a new Cursor.
   *
   * @return The Cursor.
   */
  def apply = new Cursor
}

/**
 * The Cursor is an Iterator that retrieves more Documents from the database
 * when the current batch runs out.
 */
class Cursor private extends Iterator[Document] {

  /**
   * Are there more Documents to be iterated over in the Cursor?
   *
   * @return If more documents exist in the Cursor.
   */
  def hasNext = true

  /**
   * Get the next Document in the Cursor.
   *
   * @return The next Document.
   */
  def next = new Document
}
