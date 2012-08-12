package org.scooter.io

import org.jboss.netty.channel.SimpleChannelHandler
import org.jboss.netty.channel.{ ChannelHandlerContext => Context }
import org.jboss.netty.channel.{ MessageEvent => Event }

/**
 * Handler for hooking into messages received by the channel.
 */
class Handler extends SimpleChannelHandler {

  override def messageReceived(context: Context, event: Event) = {
    println(event.getMessage)
  }
}
