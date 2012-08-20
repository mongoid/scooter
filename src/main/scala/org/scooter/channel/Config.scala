package org.scooter.channel

import java.net.SocketAddress

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelOption._
import io.netty.channel.socket.aio.{ AioEventLoop, AioSocketChannel }

object Config {

  def bootstap(address: SocketAddress) = {
    createChannel.tap {
      channel => {
        new Bootstrap.
          channel(channel).
          remoteAddress(address).
          childOption(TCP_NODELAY, true).
          childHandler(new Initializer)
      }
    }
  }

  private def createChannel = new AioSocketChannel(new AioEventLoop)
}
