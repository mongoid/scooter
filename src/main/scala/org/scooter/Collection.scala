package org.scooter

import org.scooter.bson.Document
import org.scooter.protocol.Insert

/**
 * Represents a collection in the database.
 *
 * @param database The database the Collection belongs to.
 * @param name The name of the Collection.
 */
class Collection(database: Database, name: String, session: Session) {

  /**
   * Get the full name of the Collection. Will prefix with the
   * Database name.
   *
   * @return The full name of the Collection.
   */
  def fullName = database.fullName + "." + name

  /**
   * Insert a single Document into the database.
   *
   * @param document The Document to insert.
   */
  def insert(document: Document) = {
    session.onPrimary(node => node.send(Insert(this, List(document))))
  }

  /**
   * Insert multiple Documents into the database as a batch.
   *
   * @param documents The Documents to insert.
   */
  def insert(documents: Document*) = {
    session.onPrimary(node => node.send(Insert(this, documents)))
  }
}
