package org.scooter.channel

import io.netty.channel.ChannelOption

/**
 * Companion object for the Opt class.
 */
object Opt {

  /**
   * The TCP_NODELAY option.
   *
   * @return The TCP_NODELAY option.
   */
  final val TcpNoDelay = new Opt[Boolean]("TCP_NODELAY")
}

/**
 * Wrapper for the invariant ChannelOption class for options we use that
 * need to be covariant.
 *
 * @param name The String name of the option.
 */
class Opt[T<:Any](name: String) extends ChannelOption[T](name)
