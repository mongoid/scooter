package org.mongoid.scooter

class Database(session: Session, name: String) {

  /**
   * Get a Collection for the provided name.
   *
   * @param name The name of the collection.
   * @return The Collection.
   */
  def collection(name: String) = new Collection(this, name)

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
