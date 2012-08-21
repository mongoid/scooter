package org.scooter.channel

import io.netty.channel.ChannelOption

object Opt {

  final val TcpNoDelay = new Opt[Boolean]("TCP_NODELAY")
}

class Opt[+T](name: String) extends ChannelOption[T](name)
