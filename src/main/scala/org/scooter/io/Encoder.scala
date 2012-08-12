package org.scooter.io

import org.jboss.netty.buffer.ChannelBuffers._
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder
import org.jboss.netty.channel.{ Channel, ChannelHandlerContext => Context }

import org.scooter.functional.Utilities._
import org.scooter.protocol.Serializable

/**
 * Implementation of a channel encoder for encoding messages in the
 * MongoDB wire protocol.
 */
class Encoder extends OneToOneEncoder {

  /**
   * Encode the message. These should only be serializables.
   *
   * @param context The ChannelHandlerContext.
   * @param channel The Channel.
   * @param msg The message.
   *
   * @return The new ChannelBuffer.
   */
  def encode(context: Context, channel: Channel, msg: Object) = msg match {
    case message: Serializable => {
      dynamicBuffer(LITTLE_ENDIAN, 1024).tap(buf => message.serialize(buf))
    }
  }
}
