package org.scooter

import org.scooter.bson.Document
import org.scooter.protocol.Query

import scala.collection.Iterable

/**
 * Companion object to the Criteria.
 */
object Criteria {

  /**
   * Instantiate a new Criteria object with no selector.
   *
   * @param collection The Collection.
   *
   * @return The new Criteria.
   */
  def apply(collection: Collection) = {
    new Criteria(collection, new Document)
  }

  /**
   * Instantiate a new Criteria object with a selector.
   *
   * @param collection The Collection.
   * @param selector The selector Document.
   *
   * @return The new Criteria.
   */
  def apply(collection: Collection, selector: Document) = {
    new Criteria(collection, selector)
  }
}

/**
 * A chainable object that can be iterated at any point to execute the
 * database query and get the cursor.
 *
 * @param collection The Collection the Criteria executes against.
 * @param session The database Session.
 */
class Criteria(collection: Collection, selector: Document)
  extends Iterable[Document] {

  /**
   * Get the Iterator for the Criteria.
   *
   * @return The Cursor.
   */
  def iterator = new Cursor

  /**
   * Get one document matching the criteria.
   *
   * @return A single matching Document.
   */
  def one = {
    session.onPrimary(node => node.send(Query(collection, selector)))
  }

  /**
   * Get the session that this criteria belongs to.
   *
   * @return The Session.
   */
  protected[scooter] def session = collection.session
}
