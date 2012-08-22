package org.scooter.channel

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.aio.AioSocketChannel

/**
 * Initializes the I/O channel used for database communication.
 */
class Initializer extends ChannelInitializer[AioSocketChannel] {

  /**
   * Initialize the Channel.
   *
   * @param channel The AioSocketChannel.
   */
  override def initChannel(channel: AioSocketChannel) = {
    channel.pipeline.addLast(new Encoder, new Decoder, new Handler)
  }
}
