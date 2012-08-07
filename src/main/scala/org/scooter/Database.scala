package org.scooter

import language.implicitConversions

/**
 * Companion object for the Database class.
 */
object Database {

  /**
   * Implicit conversion from a Database to a Context.
   *
   * @param session The Database to convert.
   *
   * @return The Context for the session.
   */
  implicit def wrapContext(database: Database) = database.context
}

/**
 * The Database represents a Database in a single MongoDB session.
 *
 * @param session The Session that contains the Database.
 * @param name The name of the Database.
 */
case class Database(session: Session, name: String) {

  /**
   * Get a Collection for the provided name.
   *
   * @param name The name of the collection.
   *
   * @return The Collection.
   */
  def collection(name: String) = new Collection(this, name)

  /**
   * Get the context for this Database.
   *
   * @return The Context.
   */
  def context = session.context

  /**
   * Gets the full name of the Database.
   *
   * @return The full name of the Database.
   */
  def fullName = name
}
