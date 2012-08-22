package org.scooter.channel

import java.net.SocketAddress

import io.netty.bootstrap.Bootstrap
import io.netty.channel.socket.aio.{ AioEventLoopGroup, AioSocketChannel }

import org.scooter.channel.Opt._
import org.scooter.functional.Utilities._

/**
 * Configuration object for setting up the channel options.
 */
object Config {

  /**
   * Bootstrap the Channel with our internal options.
   *
   * @param address The Remote address to connect to.
   *
   * @return The bootstrapped Channel.
   */
  def bootstrap(address: SocketAddress) = {
    createChannel.tap {
      channel => {
        (new Bootstrap).
          channel(channel).
          handler(new Initializer).
          option(TcpNoDelay, true).
          remoteAddress(address)
      }
    }
  }

  /**
   * Create the new AIO channel.
   *
   * @return The Channel.
   */
  private def createChannel = new AioSocketChannel(new AioEventLoopGroup)
}
