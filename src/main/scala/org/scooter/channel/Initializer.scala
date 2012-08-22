package org.scooter.channel

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.aio.{ AioSocketChannel => Channel }

/**
 * Initializes the I/O channel used for database communication.
 */
class Initializer extends ChannelInitializer[Channel] {

  /**
   * Initialize the Channel.
   *
   * @param channel Channel.
   */
  def initChannel(channel: Channel) = {
    channel.pipeline.addLast(new Encoder, new Decoder)
  }
}
