package org.scooter.channel

import io.netty.channel.{ ChannelHandlerContext => Context }
import io.netty.channel.ChannelInboundMessageHandlerAdapter

import java.util.concurrent.ConcurrentHashMap

import org.scooter.protocol.Reply

import scala.concurrent.SyncVar

/**
 * Handler for hooking into messages received by the channel.
 */
class Handler extends ChannelInboundMessageHandlerAdapter[Reply] {

  /**
   * Get a reply given the id of the original Request. This id is generated
   * automatically by the request header when created.
   *
   * @param requestId The original Request id.
   *
   * @return The SyncVar of the Reply.
   */
  def reply(requestId: Int) = {
    replies.putIfAbsent(requestId, new SyncVar[Reply])
    replies.get(requestId).get
  }

  /**
   * Holds all the replies that come back from the database, until they
   * are read and removed from the map.
   *
   * @return The replies map.
   */
  private val replies = new ConcurrentHashMap[Int, SyncVar[Reply]]

  /**
   * Receive the Reply message event.
   *
   * @param context The handler context.
   * @param reply The Reply.
   */
  def messageReceived(context: Context, reply: Reply) = {
    Option(replies.remove(reply.originalId)).map { _.put(reply) }
  }
}
