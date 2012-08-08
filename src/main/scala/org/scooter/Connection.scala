package org.scooter

import java.net.SocketAddress
import java.util.concurrent.{ Executors => Ex }

import org.jboss.netty.bootstrap.{ ClientBootstrap => Bootstrap }
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.socket.nio.{ NioClientSocketChannelFactory => Factory }

import org.scooter.io.Pipeline
import org.scooter.protocol.Serializable

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
    new Connection(future(address).awaitUninterruptibly.getChannel)
  }

  /**
   * Get the ClientBootstrap to use with the connection.
   *
   * @return The ClientBootstrap.
   */
  private def bootstrap = {
    val boot = new Bootstrap(factory)
    boot.setPipelineFactory(new Pipeline)
    boot
  }

  /**
   * Get the ChannelFuture for the provided address.
   *
   * @param address The SocketAddress.
   *
   * @return The ChannelFuture.
   */
  private def future(address: SocketAddress) = bootstrap.connect(address)

  /**
   * Get the NioClientSocketChannelFactory to use in creating the bootstrap.
   *
   * @return The new NioClientSocketChannelFactory.
   */
  private def factory = {
    new Factory(Ex.newCachedThreadPool, Ex.newCachedThreadPool)
  }
}

/**
 * The Connection represents a socket connection from the driver to the
 * database server.
 *
 * @param channel The SocketChannel used in the Connection.
 */
case class Connection(channel: Channel) {

  /**
   * Write the Message to the socket.
   *
   * @param message The Message to write.
   */
  def write(message: Serializable) = channel.write(message)

  /**
   * Write multiple messages to the socket.
   *
   * @param messages The Messages to write.
   */
  def write(messages: Array[Serializable]): Unit = messages.foreach(write)
}
