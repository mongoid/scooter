package org.scooter

import java.net.SocketAddress
import java.util.concurrent.{ Executors => Ex }

import org.jboss.netty.bootstrap.{ ClientBootstrap => Bootstrap }
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.socket.nio.{ NioClientSocketChannelFactory => Factory }

import org.scooter.functional.Utilities._
import org.scooter.io.Pipeline
import org.scooter.protocol.Request

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
    new Bootstrap(factory).tap(boot => boot.setPipelineFactory(new Pipeline))
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
   * Write the Request to the socket.
   *
   * @param message The Request to write.
   */
  def write(request: Request) = channel.write(request)
}
