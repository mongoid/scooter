package org.scooter.channel

import java.net.SocketAddress

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelOption._
import io.netty.channel.socket.nio.{ NioEventLoopGroup, NioSocketChannel }

import org.scooter.functional.Utilities._

/**
 * Configuration object for setting up the channel options.
 */
object Config {

  /**
   * Create the new channel.
   *
   * @address The remote ScoketAddress.
   *
   * @return The Channel.
   */
  def connect(address: SocketAddress) = {
    bootstrap(address).connect.sync.channel
  }

  /**
   * Bootstrap the Channel with our internal options.
   *
   * @param address The Remote address to connect to.
   *
   * @return The bootstrapped Channel.
   *
   * @todo Fix ChannelOptions to use TCP_NODELAY.
   */
  private def bootstrap(address: SocketAddress) = {
    (new Bootstrap).
      group(new NioEventLoopGroup).
      channel(new NioSocketChannel).
      handler(new Initializer).
      remoteAddress(address)
  }
}
