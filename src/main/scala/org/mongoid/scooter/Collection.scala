package org.mongoid.scooter

/**
 * Represents a collection in the database.
 *
 * @param database The database the Collection belongs to.
 * @param name The name of the Collection.
 */
class Collection(database: Database, name: String) {

  /**
   * A Collection is equal to another if the names are the same.
   *
   * @param other The object to compare to.
   * @return If the objects are equal.
   */
  override def equals(other: Any) : Boolean = {
    other.isInstanceOf[Collection] && (this.hashCode == other.hashCode)
  }

  /**
   * Generate a hash code for the Collection from the name.
   *
   * @return The hash code from the name.
   */
  override def hashCode = name.hashCode

  /**
   * Insert documents into the database.
   *
   * @param documents An array of documents to insert.
   */
  def insert(documents: Array[Map[String, Any]]) = {
    database.insert(documents)
  }

  /**
   * Insert a single document into the database.
   *
   * @param document A document to insert.
   */
  def insert(document: Map[String, Any]) = {
    database.insert(document)
  }
}
