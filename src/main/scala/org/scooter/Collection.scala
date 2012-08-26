package org.scooter

import org.scooter.bson.{ Document, Writable }
import org.scooter.bson.Serialization._
import org.scooter.protocol.{ Insert, Query }

/**
 * Companion object for the Collection.
 */
object Collection {

  /**
   * Instantiate a new Collection.
   *
   * @param database The Collection's Database.
   * @param name The full name of the Collection.
   *
   * @return The Collection.
   */
  def apply(database: Database, name: String) = {
    new Collection(database, name)
  }
}

/**
 * Represents a collection in the database.
 *
 * @param database The database the Collection belongs to.
 * @param name The name of the Collection.
 */
class Collection private (database: Database, val name: String) {

  /**
   * Drops this collection.
   *
   * @return The Reply.
   */
  def drop = database.command("drop" -> name)

  /**
   * Get all documents that are in the Collection.
   *
   * @example Find all documents.
   *  session.users.find
   *
   * @return The chainable Criteria object for all documents.
   */
  def find = Criteria(this)

  /**
   * Get all documents matching the selector in the Collection.
   *
   * @example Find mtching documents for the selector.
   *  session.users.find("firstName" -> "Sid")
   *
   * @param selector The MongoDB selector in Writable form.
   *
   * @return The chainable Criteria object for the matching documents.
   */
  def find(selector: (String, Writable)*) = {
    Criteria(this, Document(selector: _*))
  }

  /**
   * Get the full namespaced name of the Collection.
   *
   * @return The full namespaced name.
   */
  val namespacedName = database.name + "." + name

  /**
   * Insert a single Document into the database.
   *
   * @param document The Document to insert.
   */
  def insert(document: Document) = {
    session.onPrimary {
      (node: Node) => node.send(Insert(this, List(document)))
    }
  }

  /**
   * Insert multiple Documents into the database as a batch.
   *
   * @param documents The Documents to insert.
   */
  def insert(documents: Document*) = {
    session.onPrimary {
      (node: Node) => node.send(Insert(this, documents))
    }
  }

  /**
   * Get the session this Collection belongs to.
   *
   * @return The Session.
   */
  protected[scooter] def session = database.session
}
