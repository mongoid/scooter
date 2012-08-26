package org.scooter.channel

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled._
import io.netty.channel.{ ChannelHandlerContext => Context }
import io.netty.handler.codec.MessageToByteEncoder

import org.scooter.protocol.Encodable

/**
 * Encodes Encodable messages to the output ByteBuf.
 */
class Encoder extends MessageToByteEncoder[Encodable] {

  /**
   * Encode the outbound message to the ByteBuf, ensuring we use little
   * endian as the byte order.
   *
   * @param context The ChannelHandlerContext.
   * @param message The Encodable Message.
   * @param outbound The outbound ByteBuf.
   */
  def encode(context: Context, message: Encodable, outbound: ByteBuf) = {
    message.encode(outbound.order(LITTLE_ENDIAN))
  }
}
