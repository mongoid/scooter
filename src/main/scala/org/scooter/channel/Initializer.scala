package org.scooter.channel

import io.netty.channel.{ Channel, ChannelInitializer }

class Initializer extends ChannelInitializer {

  def initChannel(channel: Channel) = {
    channel.pipeline.addLast(new Encoder, new Decoder, new Handler)
  }
}
