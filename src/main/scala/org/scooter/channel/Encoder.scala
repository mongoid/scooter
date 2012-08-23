package org.scooter.channel

import io.netty.buffer.ByteBuf
import io.netty.channel.{ ChannelHandlerContext => Context }
import io.netty.handler.codec.MessageToByteEncoder

import java.nio.ByteOrder._

import org.scooter.protocol.Serializable

/**
 * Encodes Serializable messages to the output ByteBuf.
 */
class Encoder extends MessageToByteEncoder[Serializable] {

  /**
   * Encode the outbound message to the ByteBuf, ensuring we use little
   * endian as the byte order.
   *
   * @param context The ChannelHandlerContext.
   * @param message The Serializable Message.
   * @param outbound The outbound ByteBuf.
   */
  def encode(context: Context, message: Serializable, outbound: ByteBuf) = {
    message.serialize(outbound.order(LITTLE_ENDIAN))
  }
}