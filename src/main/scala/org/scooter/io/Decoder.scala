package org.scooter.io

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.frame.FrameDecoder
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext => Context }

class Decoder extends FrameDecoder {

  def decode(context: Context, channel: Channel, buffer: ChannelBuffer): Object = null
    // readable(buffer) {
      // respondable(buffer)(length => new Reply(buffer.readBytes(length)))
    // }
  // }

  // private def readable(buffer: ChannelBuffer)(func: => Unit) = {
    // if (buffer.readableBytes >= 4) {
      // buffer.markReaderIndex
      // func
    // }
  // }

  // private def respondable(buffer: ChannelBuffer)(func: Int => Reply) = {
    // val length = buffer.readInt(buffer)
    // buffer.resetReaderIndex

    // if (buffer.readableBytes >= length) {
      // func(length)
    // }
    // else {
      // buffer.resetReaderIndex; null
    // }
  // }
}
