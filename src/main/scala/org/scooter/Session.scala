package org.scooter

import java.net.SocketAddress

import org.scooter.functional.Utilities._

import scala.language.implicitConversions

/**
 * Companion object for the Session class.
 */
object Session {

  /**
   * Create a new session from the provided strings of host:port pairs.
   *
   * @param addresses The strings in the form "127.0.0.1:27017".
   *
   * @return The Session.
   */
  def apply(addresses: String*) = new Session(socketAddresses(addresses))

  /**
   * Implicit conversion from a Session to a Database.
   *
   * @param session The Session to convert.
   *
   * @return The Database for the session.
   */
  implicit def wrapDatabase(session: Session) = session.database
}

/**
 * The Session is the main entry point in Scooter for dealing with MongoDB.
 * Most operations start from an instance of a Session.
 *
 * @param hosts A sequence of SocketAddresses.
 */
class Session(hosts: Seq[SocketAddress]) extends Dynamic {

  /**
   * The current Database the Session is operating with.
   *
   * @return The current Database.
   */
  var database: Database = null;

  // val cluster = Cluster(hosts)

  /**
   * Use of Scala's experimental Dyanmic invokation in order to simulate
   * something similar to Ruby's method_missing. This way a collection
   * can be accessed from the session simply by just calling a method that
   * is the collection name.
   *
   * @param name The name of the Collection.
   *
   * @return The Collection for the provided name.
   */
  def selectDynamic(name: String) = database.collection(name)

  /**
   * Use the database as specified by the provided name.
   *
   * @param name The name of the Database.
   */
  def use(name: String) = this.database = new Database(this, name)
}
