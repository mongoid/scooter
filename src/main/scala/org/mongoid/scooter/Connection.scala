package org.mongoid.scooter

import org.mongoid.scooter.protocol.Message

import java.net.SocketAddress
import java.nio.{ ByteBuffer, ByteOrder }
import java.nio.channels.SocketChannel

object Connection {

  def apply(address: SocketAddress) : Connection = {
    val channel = SocketChannel.open(address)
    channel.configureBlocking(false)
    new Connection(channel)
  }
}

class Connection(channel: SocketChannel) {

  def write(message: Message) : Unit = {
    var buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN)
    message.bsonSerialize(buffer)
    buffer.flip
    channel.write(buffer)
  }
}
