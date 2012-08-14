package org.scooter.io

import java.net.SocketAddress
import java.nio.ByteOrder._
import java.util.concurrent.{ Executors => Ex }

import org.jboss.netty.bootstrap.ClientBootstrap
import org.jboss.netty.buffer.{ HeapChannelBufferFactory => BufferFactory }
import org.jboss.netty.channel.socket.nio.{ NioClientSocketChannelFactory => Factory }

import org.scooter.functional.Utilities._

/**
 * The singleton bootstrap for Scooter connections.
 */
object Bootstrap {

  /**
   * Get the Channel for this bootstrap.
   *
   * @param address The SocketAddress to connect to.
   *
   * @return The Channel.
   */
  protected[scooter] def channel(address: SocketAddress) = {
    future(address).awaitUninterruptibly.getChannel
  }

  /**
   * Get the ClientBootstrap to use with the connection.
   *
   * @note This sets some default options for the bootstrap:
   *  - The default byte order should always be little endian.
   *  - Use TCP_NODELAY at all times.
   *  - Set the pipeline factory to our pipeline.
   *
   * @return The ClientBootstrap.
   */
  private def bootstrap = {
    new ClientBootstrap(factory).tap(boot => configureBootstrap(boot))
  }

  /**
   * Configure the provided boostrap with the default options.
   *
   * @param bootstrap The ClientBootstrap.
   */
  private def configureBootstrap(bootstrap: ClientBootstrap) = {
    bootstrap.setOption("bufferFactory", new BufferFactory(LITTLE_ENDIAN))
    bootstrap.setOption("tcpNoDelay", true)
    bootstrap.setPipelineFactory(new Pipeline)
  }

  /**
   * Get the NioClientSocketChannelFactory to use in creating the bootstrap.
   *
   * @return The new NioClientSocketChannelFactory.
   */
  private def factory = {
    new Factory(Ex.newCachedThreadPool, Ex.newCachedThreadPool)
  }

  /**
   * Get the ChannelFuture for the provided address.
   *
   * @param address The SocketAddress.
   *
   * @return The ChannelFuture.
   */
  private def future(address: SocketAddress) = bootstrap.connect(address)
}
