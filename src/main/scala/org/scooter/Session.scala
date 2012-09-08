package org.scooter

import io.netty.channel.{ ChannelFuture => Future }

import java.net.SocketAddress

import org.scooter.functional.Utilities._
import org.scooter.protocol.Reply

import scala.language.dynamics

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
  var currentDatabase: Database = null

  /**
   * Use of Scala's Dyanmic invokation in order to simulate the same
   * way collections are accessed via the Mongo console.
   *
   * @example Get the users collection.
   *  session.users
   *
   * @param name The name of the Collection.
   *
   * @return The Collection for the provided name.
   */
  def selectDynamic(name: String) = {
    currentDatabase.collection(name)
  }

  /**
   * Use the database as specified by the provided name.
   *
   * @param name The name of the Database.
   */
  def use(name: String) = {
    currentDatabase = Database(this, name)
  }

  /**
   * Execute the provided function in the context of the primary Node.
   *
   * @param func The function to execute.
   *
   * @return The Reply from the database.
   */
  def onPrimary(func: Node => Reply) = {
    cluster.onPrimary(func)
  }

  /**
   * Execute the provided function in the context of the primary Node.
   *
   * @param func The function to execute.
   *
   * @return The future.
   */
  def onPrimary(func: Node => Future) = {
    cluster.onPrimary(func)
  }

  /**
   * Shutdown all nodes in the Session.
   */
  def shutdown = cluster.shutdown

  /**
   * Get the cluster of nodes for this session.
   *
   * @return The session's Cluster.
   */
  private final lazy val cluster = Cluster(hosts)
}
