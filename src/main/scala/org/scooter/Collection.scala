package org.scooter

/**
 * Represents a collection in the database.
 *
 * @param database The database the Collection belongs to.
 * @param name The name of the Collection.
 */
case class Collection(database: Database, name: String) {

  /**
   * Get the full name of the Collection. Will prefix with the
   * Database name.
   *
   * @return The full name of the Collection.
   */
  def fullName = database.fullName + "." + name

  /**
   * Insert documents into the database.
   *
   * @param documents An array of documents to insert.
   */
  def insert(documents: Array[_<:Map[String, Any]]) = database.insert(documents)

  /**
   * Insert a single document into the database.
   *
   * @param document A document to insert.
   */
  def insert(document: Map[String, Any]) = database.insert(document)
}
