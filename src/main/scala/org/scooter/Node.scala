package org.scooter

import java.net.SocketAddress

import org.scooter.protocol.Request

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
   * Send the provided request to the database.
   *
   * @param message The Request to send.
   */
  protected[scooter] def send(request: Request) = connection.write(request)
}
