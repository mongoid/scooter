package org.scooter.channel

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled._
import io.netty.channel.{ ChannelHandlerContext => Context }
import io.netty.handler.codec.LengthFieldBasedFrameDecoder

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
   * @param buffer The ByteBuf to read from.
   *
   * @return The database Reply.
   */
  override def decode(context: Context, buffer: ByteBuf): Object = {
    reply(super.decode(context, buffer.order(LITTLE_ENDIAN)).asInstanceOf[ByteBuf])
  }

  /**
   * Return the reply or null depending on the frame.
   *
   * @param buffer The extracted frame.
   *
   * @return The Reply or null.
   */
  private def reply(buffer: ByteBuf) = {
    if (buffer == null) null else Reply.deserialize(buffer)
  }
}
