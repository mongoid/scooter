package org.mongoid.scooter

class Database(session: Session, name: String) {

  override def equals(other: Any) : Boolean = {
    other.isInstanceOf[Database] && (this.hashCode == other.hashCode)
  }

  override def hashCode = name.hashCode
}
