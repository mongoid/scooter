package org.scooter.io

import org.jboss.netty.channel.SimpleChannelHandler
import org.jboss.netty.channel.{ ChannelHandlerContext => Context }
import org.jboss.netty.channel.{ MessageEvent => Event }

/**
 * Handler for hooking into messages received by the channel.
 */
class Handler extends SimpleChannelHandler {

  /**
   * Receive the Reply message event.
   *
   * @param context The handler context.
   * @param event The MessageEvent.
   */
  override def messageReceived(context: Context, event: Event) = {}
}
