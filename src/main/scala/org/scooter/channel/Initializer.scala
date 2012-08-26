package org.scooter.channel

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.nio.NioSocketChannel

/**
 * Initializes the I/O channel used for database communication.
 */
class Initializer protected[scooter] extends ChannelInitializer[NioSocketChannel] {

  /**
   * Initialize the Channel.
   *
   * @param channel The NioSocketChannel.
   */
  override def initChannel(channel: NioSocketChannel) = {
    channel.pipeline.addLast(new Encoder, new Decoder, new Handler)
  }
}
