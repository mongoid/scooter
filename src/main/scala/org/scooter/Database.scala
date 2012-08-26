package org.scooter

import org.scooter.bson.{ Document, Writable }
import org.scooter.bson.Serialization._
import org.scooter.protocol.{ Query, Reply }

/**
 * Companion object to a Database.
 */
object Database {

  /**
   * Instantiate a new Database.
   *
   * @param session The Session.
   * @param name The name of the Database.
   *
   * @return The Database.
   */
  def apply(session: Session, name: String) = {
    new Database(session, name)
  }
}

/**
 * The Database represents a Database in a single MongoDB session.
 *
 * @param session The Session that contains the Database.
 * @param name The name of the Database.
 */
class Database (val session: Session, val name: String) {

  /**
   * Get a Collection for the provided name.
   *
   * @param name The name of the collection.
   *
   * @return The Collection.
   */
  def collection(name: String) = Collection(this, name)

  /**
   * Execute a command ($cmd) against this database.
   *
   * @example Execute a drop collection command.
   *  database.command("drop" -> "users")
   *
   * @param instruction The command instructions.
   *
   * @return The Reply.
   */
  def command(instruction: (String, Writable)*): Reply = {
    session.onPrimary {
      (node: Node) => node.send(Query(Document(instruction: _*)))
    }
  }
}
