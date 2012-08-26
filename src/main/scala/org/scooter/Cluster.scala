package org.scooter

import io.netty.channel.{ ChannelFuture => Future }

import java.net.SocketAddress

import org.scooter.protocol.Reply

/**
 * Companion object to a Cluster.
 */
object Cluster {

  /**
   * Create the new Cluster given the provided socket addresses.
   *
   * @param addresses The SocketAddresses.
   *
   * @return The Cluster.
   */
  protected[scooter] def apply(addresses: Seq[SocketAddress]) = {
    new Cluster(
      addresses.foldLeft(List[Node]())(
        (nodes, address) => nodes.+:(Node(address))
      )
    )
  }
}

/**
 * The Cluster is a group of Nodes that sit on separate servers.
 *
 * @param nodes The sequence of Nodes.
 */
class Cluster protected[scooter](nodes: Seq[Node]) {

  /**
   * Execute the provided function in the context of the primary Node.
   *
   * @param func The function to execute.
   *
   * @return The Reply from the database.
   */
  protected[scooter] def onPrimary(func: Node => Reply) = {
    func(primary)
  }

  /**
   * Execute the provided function in the context of the primary Node.
   *
   * @param func The function to execute.
   *
   * @return The future.
   */
  protected[scooter] def onPrimary(func: Node => Future) = {
    func(primary)
  }

  /**
   * Get the primary node.
   *
   * @return The primary Node.
   */
  private def primary = nodes(0)
}
