package org.scooter

import java.net.InetSocketAddress
import java.net.SocketAddress

/**
 * Provides utility functions related to hosts and SocketAddresses.
 */
object Host {

  /**
   * Get a SocketAddress from the provided String.
   *
   * @param address The String address.
   *
   * @return The SocketAddress.
   */
  def socketAddress(address: String) = {
    val split = address.split(":")
    new InetSocketAddress(split(0), split(1).toInt)
  }

  /**
   * Get SocketAddresses from the provided Strings.
   *
   * @param addresses The String addresses.
   *
   * @return The sequence of SocketAddresses.
   */
  def socketAddresses(addresses: Seq[String]) = {
    addresses.foldLeft(List[SocketAddress]())(
      (list, address) => list.+:(socketAddress(address))
    )
  }
}
