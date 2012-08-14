package org.scooter

import java.net.SocketAddress

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
class Cluster(nodes: Seq[Node]) {

  /**
   * Execute the provided function in the context of the primary Node.
   *
   * @param func The function to execute.
   *
   * @return The result of the function.
   */
  protected[scooter] def onPrimary(func: Node => Any) = func(primary)

  /**
   * Get the primary node.
   *
   * @return The primary Node.
   */
  private def primary = nodes(0)
}
