package org.scooter

import java.net.SocketAddress
import java.nio.channels.SocketChannel

import org.scooter.bson.MutableBuffer
import org.scooter.protocol.Message

/**
 * Companion object for the Connection class.
 */
object Connection {

  /**
   * Instantiate a new Connection.
   *
   * @param address The SocketAddress to connect to.
   *
   * @return The new Connection.
   */
  def apply(address: SocketAddress) = {
    new Connection(SocketChannel.open(address))
  }
}

/**
 * The Connection represents a socket connection from the driver to the
 * database server.
 *
 * @param channel The SocketChannel used in the Connection.
 */
class Connection(channel: SocketChannel) {

  /**
   * Write the Message to the socket.
   *
   * @param message The Message to write.
   */
  def write(message: Message) = {
    var buffer = MutableBuffer(64)
    message.serialize(buffer)
    buffer.flip
    channel.write(buffer)
  }

  /**
   * Write multiple messages to the socket.
   *
   * @param messages The Messages to write.
   */
  def write(messages: Array[Message]): Unit = messages.foreach(write)
}
