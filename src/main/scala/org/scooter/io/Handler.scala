package org.scooter.io

import java.util.concurrent.ConcurrentHashMap

import org.jboss.netty.channel.SimpleChannelHandler
import org.jboss.netty.channel.{ ChannelHandlerContext => Context }
import org.jboss.netty.channel.{ MessageEvent => Event }

import org.scooter.protocol.Reply

import scala.concurrent.SyncVar

/**
 * Handler for hooking into messages received by the channel.
 */
class Handler extends SimpleChannelHandler {

  /**
   * Get a reply given the id of the original Request. This id is generated
   * automatically by the request header when created.
   *
   * @param original The original Request id.
   *
   * @return The SyncVar of the Reply.
   */
  def reply(original: Int) = {
    replies.putIfAbsent(original, new SyncVar[Reply])
    replies.get(original)
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
   * @param event The MessageEvent.
   */
  override def messageReceived(context: Context, event: Event) = {
    event.getMessage match {
      case reply: Reply => {
        Option(replies.remove(reply.header.original)).map { _.put(reply) }
      }
    }
  }
}
