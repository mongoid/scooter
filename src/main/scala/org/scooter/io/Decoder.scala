package org.scooter.io

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.frame.FrameDecoder
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext => Context }

import org.scooter.protocol.Reply

/**
 * Decodes bytes from the database server and converts the frames of bytes
 * into Reply objects.
 */
class Decoder extends FrameDecoder {

  /**
   * Decode the reply from the database, returning a Reply object.
   *
   * @param context The handler context.
   * @param channel The Channel.
   * @param buffer The ChannelBuffer to read from.
   *
   * @return The database Reply.
   */
  def decode(context: Context, channel: Channel, buffer: ChannelBuffer): Object = {

    // Break from processing if we cannot read the length of the message.
    if (buffer.readableBytes < 4) return null

    // Mark the buffer and read the expected length of the message.
    buffer.markReaderIndex
    val length = buffer.readInt

    // Break from processing if we cannot read the entire message. Will reset the
    // buffer's index so it can process again as normal.
    if (buffer.readableBytes < length) {
      buffer.resetReaderIndex
      return null
    }

    // Deserialize the database reply.
    Reply.deserialize(buffer.readBytes(length))
  }
}
