package org.scooter.io

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext => Context }
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder

import org.scooter.protocol.Reply

/**
 * Decodes bytes from the database server and converts the frames of bytes
 * into Reply objects.
 */
class Decoder extends LengthFieldBasedFrameDecoder(2048, 0, 4, -4, 0) {

  /**
   * Decode the reply from the database, returning a Reply object.
   *
   * @param context The handler context.
   * @param channel The Channel.
   * @param buffer The ChannelBuffer to read from.
   *
   * @return The database Reply.
   */
  override def decode(context: Context, channel: Channel, buffer: Buffer): Object = {
    reply(super.decode(context, channel, buffer).asInstanceOf[Buffer])
  }

  /**
   * Return the reply or null depending on the frame.
   *
   * @param buffer The extracted frame.
   *
   * @return The Reply or null.
   */
  private def reply(buffer: Buffer) = {
    if (buffer == null) null else Reply.deserialize(buffer)
  }
}
