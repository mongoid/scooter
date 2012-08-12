package org.scooter.io

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext => Context }

import org.scooter.protocol.Reply

/**
 * Decodes bytes from the database server and converts the frames of bytes
 * into Reply objects.
 */
class Decoder extends LengthFieldBasedFrameDecoder(2048, 0, 4, 0, 4) {

  /**
   * Decode the reply from the database, returning a Reply object.
   *
   * @param context The handler context.
   * @param channel The Channel.
   * @param buffer The ChannelBuffer to read from.
   *
   * @return The database Reply.
   */
  override def decode(context: Context, channel: Channel, buffer: ChannelBuffer): Object = {
    // println("EXPECTED LENGTH: " + buffer.getInt(0))
    // println(Reply.deserialize(buffer))
    reply(super.decode(context, channel, buffer).asInstanceOf[ChannelBuffer])
  }

  /**
   * Return the reply or null depending on the frame.
   *
   * @param buffer The extracted frame.
   *
   * @return The Reply or null.
   */
  private def reply(buffer: ChannelBuffer) = {
    // println("HEY MA:")
    // println(buffer)
    if (buffer == null) null else Reply.deserialize(buffer)
  }
}
