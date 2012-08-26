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
    new Criteria(collection, new Document, 0, 0)
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
    new Criteria(collection, selector, 0, 0)
  }

  /**
   * Instantiate a new Criteria object with a selector and skip
   *
   * @param collection The Collection.
   * @param selector The selector Document.
   * @param skip The number of Documents to skip.
   *
   * @return The new Criteria.
   */
  def apply(collection: Collection, selector: Document, skip: Int) = {
    new Criteria(collection, selector, skip, 0)
  }

  /**
   * Instantiate a new Criteria object with a selector, skip and limit
   *
   * @param collection The Collection.
   * @param selector The selector Document.
   * @param skip The number of Documents to skip.
   * @param limit The number of Documents to return.
   *
   * @return The new Criteria.
   */
  def apply(collection: Collection, selector: Document, skip: Int, limit: Int) = {
    new Criteria(collection, selector, skip, limit)
  }
}

/**
 * A chainable object that can be iterated at any point to execute the
 * database query and get the cursor.
 *
 * @note Criteria are immutable. Each operation that would modify the
 *   Criteria will return a new instance with the additional options.
 *
 * @param collection The Collection the Criteria executes against.
 * @param session The database Session.
 */
class Criteria (
  collection: Collection, selector: Document, skip: Int, limit: Int
) extends Iterable[Document] {

  /**
   * Get the Iterator for the Criteria.
   *
   * @return The Cursor.
   */
  def iterator = Cursor.apply

  /**
   * Limit the Criteria to the provided number of Documents.
   *
   * @param value The number of Documents to return.
   *
   * @return The new Criteria with limit applied.
   */
  def limit(value: Int): Criteria = {
    Criteria(collection, selector, skip, value)
  }

  /**
   * Get one document that matches this Criteria.
   *
   * @return A single matching Document.
   */
  def one = {
    session.onPrimary {
      (node: Node) => node.send(Query(collection, selector, skip, -1))
    }
  }

  /**
   * Limit the Criteria to the provided number of Documents.
   *
   * @param value The number of Documents to return.
   *
   * @return The new Criteria with limit applied.
   */
  def skip(value: Int): Criteria = {
    Criteria(collection, selector, value, limit)
  }

  /**
   * Get the session that this criteria belongs to.
   *
   * @return The Session.
   */
  protected[scooter] def session = collection.session
}
