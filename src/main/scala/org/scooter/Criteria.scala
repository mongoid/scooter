package org.scooter

import org.scooter.bson.Document

import scala.collection.Iterable

/**
 * Companion object to the Criteria.
 */
object Criteria {

  /**
   * Instantiate a new Criteria object with no selector.
   *
   * @param collection The Collection.
   * @param session The Session.
   *
   * @return The new Criteria.
   */
  def apply(collection: Collection, session: Session) = {
    new Criteria(collection, new Document, session)
  }

  /**
   * Instantiate a new Criteria object with a selector.
   *
   * @param collection The Collection.
   * @param selector The selector Document.
   * @param session The Session.
   *
   * @return The new Criteria.
   */
  def apply(collection: Collection, selector: Document, session: Session) = {
    new Criteria(collection, selector, session)
  }
}

/**
 * A chainable object that can be iterated at any point to execute the
 * database query and get the cursor.
 *
 * @param collection The Collection the Criteria executes against.
 * @param session The database Session.
 */
class Criteria(collection: Collection, selector: Document, session: Session)
  extends Iterable[Document] {

  /**
   * Get the Iterator for the Criteria.
   *
   * @return The Cursor.
   */
  def iterator = new Cursor
}
