package org.scooter

import language.implicitConversions

/**
 * The Database represents a Database in a single MongoDB session.
 *
 * @param session The Session that contains the Database.
 * @param name The name of the Database.
 */
class Database(session: Session, name: String) {

  /**
   * Get a Collection for the provided name.
   *
   * @param name The name of the collection.
   *
   * @return The Collection.
   */
  def collection(name: String) = new Collection(this, name, session)

  /**
   * Gets the full name of the Database.
   *
   * @return The full name of the Database.
   */
  def fullName = name
}
