package org.scooter.channel

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled._
import io.netty.channel.{ ChannelHandlerContext => Context }
import io.netty.handler.codec.ByteToMessageDecoder

import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.protocol.Reply

/**
 * Decodes bytes from the database server and converts the frames of bytes
 * into Reply objects.
 */
class Decoder extends ByteToMessageDecoder[Reply] {

  /**
   * Decode the reply from the database, returning a Reply object.
   *
   * @param context The handler context.
   * @param input The ByteBuf to read from.
   *
   * @return The database Reply.
   */
  def decode(context: Context, input: ByteBuf): Reply = {
    val buffer = input.order(LITTLE_ENDIAN)
    if (buffer.isReadable) Reply.deserialize(buffer.readFrame) else return null
  }
}
