package org.scooter

import java.net.SocketAddress

import org.scooter.protocol.Message

/**
 * Companion object for a Node.
 */
object Node {

  /**
   * Create a new Node given the SocketAddress.
   *
   * @param address The SocketAddress.
   *
   * @return The Node.
   */
  def apply(address: SocketAddress) = {
    new Node(Connection(address))
  }
}

/**
 * A Node wraps a single connection to a database server.
 *
 * @param connection The db Connection.
 */
class Node(connection: Connection) {

  /**
   * Send the provided Message to the database.
   *
   * @param message The Message to send.
   */
  protected[scooter] def send(message: Message) = connection.write(message)
}
