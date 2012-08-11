package org.scooter

import java.net.SocketAddress

object Cluster {

  def apply(addresses: Seq[SocketAddress]) = {
    // new Cluster(nodes)
  }
}

case class Cluster(nodes: Seq[Node]) {

}
