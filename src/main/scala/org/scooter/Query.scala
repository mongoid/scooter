package org.scooter

import org.scooter.bson.Document

import scala.collection.Iterable

/**
 * Companion object to the Query.
 */
object Query {

  /**
   * Instantiate a new Query object with no selector.
   *
   * @param collection The Collection.
   * @param session The Session.
   *
   * @return The new Query.
   */
  def apply(collection: Collection, session: Session) = {
    new Query(collection, new Document, session)
  }

  /**
   * Instantiate a new Query object with a selector.
   *
   * @param collection The Collection.
   * @param selector The selector Document.
   * @param session The Session.
   *
   * @return The new Query.
   */
  def apply(collection: Collection, selector: Document, session: Session) = {
    new Query(collection, selector, session)
  }
}

/**
 * A chainable object that can be iterated at any point to execute the
 * database query and get the cursor.
 *
 * @param collection The Collection the Query executes against.
 * @param session The database Session.
 */
class Query(collection: Collection, selector: Document, session: Session)
  extends Iterable[Document] {

  /**
   * Get the Iterator for the Query.
   *
   * @return The Cursor.
   */
  def iterator = new Cursor
}
