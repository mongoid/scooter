package org.scooter

import java.net.SocketAddress
import java.nio.ByteOrder._
import java.util.concurrent.{ Executors => Ex }

import org.jboss.netty.bootstrap.{ ClientBootstrap => Bootstrap }
import org.jboss.netty.buffer.{ HeapChannelBufferFactory => BufferFactory }
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.socket.nio.{ NioClientSocketChannelFactory => Factory }

import org.scooter.functional.Utilities._
import org.scooter.io.{ Handler, Pipeline }
import org.scooter.protocol.{ Command, Request }

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
  protected[scooter] def apply(address: SocketAddress) = {
    new Connection(future(address).awaitUninterruptibly.getChannel)
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
    new Bootstrap(factory).tap(
      boot => {
        boot.setOption("bufferFactory", new BufferFactory(LITTLE_ENDIAN))
        boot.setOption("tcpNoDelay", true)
        boot.setPipelineFactory(new Pipeline)
      }
    )
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
   * Write the Command to the socket.
   *
   * @param command The Command to write.
   */
  protected[scooter] def send(command: Command) = channel.write(command)

  /**
   * Write the Request to the socket and return the Reply.
   *
   * @note The Reply is wrapped in a SynVar which will wait to return
   *   until the response has come back from the database.
   *
   * @param request The Request to write.
   *
   * @return The Reply once it is available.
   */
  protected[scooter] def send(request: Request) = {
    channel.write(request)
    handler.reply(request.header.request)
  }

  /**
   * Get the scooter io handler, the last object in the pipeline.
   *
   * @return The Handler.
   */
  private def handler: Handler = {
    channel.getPipeline.getLast.asInstanceOf[Handler]
  }
}
