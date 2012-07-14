package org.mongoid.scooter

import language.implicitConversions

/**
 * Companion object for the Database class.
 */
object Database {

  /**
   * Implicit conversion from a Database to a Context.
   *
   * @param session The Database to convert.
   * @return The Context for the session.
   */
  implicit def delegateToContext(database: Database) = database.context
}

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
   * A Database is equal to another if the names are the same.
   *
   * @param other The object to compare to.
   * @return If the objects are equal.
   */
  override def equals(other: Any) : Boolean = {
    other.isInstanceOf[Database] && (this.hashCode == other.hashCode)
  }

  /**
   * Generate a hash code for the Database from the name.
   *
   * @return The hash code from the name.
   */
  override def hashCode = name.hashCode
}
