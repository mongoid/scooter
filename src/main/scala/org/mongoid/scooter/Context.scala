package org.mongoid.scooter

/**
 * The Context is an object that handles persistence operations within
 * a specific session.
 *
 * @param session The Session for the Context.
 */
class Context(session: Session) {

  /**
   * Insert documents into the database.
   *
   * @param documents An array of documents to insert.
   */
  def insert(documents: Array[_<:Map[String, Any]]) = {}

  /**
   * Insert a single document into the database.
   *
   * @param document A document to insert.
   */
  def insert(document: Map[String, Any]) = {}
}
