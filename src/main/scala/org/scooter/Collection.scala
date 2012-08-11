package org.scooter

import org.scooter.bson.{ Document, Dumpable }
import org.scooter.bson.Serialization._
import org.scooter.protocol.Insert

/**
 * Represents a collection in the database.
 *
 * @param database The database the Collection belongs to.
 * @param name The name of the Collection.
 */
class Collection(database: Database, name: String, session: Session) {

  /**
   * Get all documents that are in the Collection.
   *
   * @return The chainable Query object for all documents.
   */
  def find = Query(this, session)

  /**
   * Get all documents matching the selector in the Collection.
   *
   * @param selector The MongoDB selector in Dumpable form.
   *
   * @return The chainable Query object for the matching documents.
   */
  def find(selector: (String, Dumpable)*) = {
    Query(this, Document(selector: _*), session)
  }

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
