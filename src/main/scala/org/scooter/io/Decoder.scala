package org.scooter.io

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.frame.FrameDecoder
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext => Context }

class Decoder extends FrameDecoder {

  def decode(context: Context, channel: Channel, buffer: ChannelBuffer): Object = null
}
