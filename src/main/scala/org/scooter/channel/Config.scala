package org.scooter.channel

import java.net.SocketAddress

import io.netty.bootstrap.Bootstrap
import io.netty.channel.socket.aio.{ AioEventLoopGroup, AioSocketChannel }

import org.scooter.channel.Opt._
import org.scooter.functional.Utilities._

object Config {

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

  private def createChannel = new AioSocketChannel(new AioEventLoopGroup)
}
