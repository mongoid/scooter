package org.scooter.io

import java.util.concurrent.ConcurrentHashMap

import org.jboss.netty.channel.SimpleChannelHandler
import org.jboss.netty.channel.{ ChannelHandlerContext => Context }
import org.jboss.netty.channel.{ MessageEvent => Event }

import org.scooter.protocol.Reply

/**
 * Handler for hooking into messages received by the channel.
 */
class Handler extends SimpleChannelHandler {

  /**
   * Holds all the replies that come back from the database, until they
   * are read and removed from the map.
   *
   * @return The replies map.
   */
  private val replies = new ConcurrentHashMap[Int, Reply]

  /**
   * Receive the Reply message event.
   *
   * @param context The handler context.
   * @param event The MessageEvent.
   */
  override def messageReceived(context: Context, event: Event) = {
    event.getMessage match {
      case reply: Reply => replies.put(reply.header.originalId, reply)
    }
  }
}
