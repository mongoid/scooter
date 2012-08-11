package org.scooter

import java.net.SocketAddress

object Node {

  def apply(address: SocketAddress) = {
    new Node(Connection(address))
  }
}

case class Node(connection: Connection) {

}
