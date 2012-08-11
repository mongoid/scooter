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
  var currentDatabase: Database = null;

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
  def selectDynamic(name: String) = currentDatabase.collection(name)

  /**
   * Use the database as specified by the provided name.
   *
   * @param name The name of the Database.
   */
  def use(name: String) = currentDatabase = new Database(this, name)

  /**
   * Execute the provided function in the context of the primary Node.
   *
   * @param func The function to execute.
   *
   * @return The result of the function.
   */
  protected[scooter] def onPrimary(func: Node => Any) = cluster.onPrimary(func)

  /**
   * Get the cluster of nodes for this session.
   *
   * @return The session's Cluster.
   */
  private final lazy val cluster = Cluster(hosts)
}
