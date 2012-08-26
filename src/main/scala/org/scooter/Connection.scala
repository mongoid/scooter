package org.scooter

import io.netty.bootstrap.Bootstrap
import io.netty.channel.{ Channel, ChannelFuture }
import io.netty.channel.socket.nio.{ NioEventLoopGroup, NioSocketChannel }

import java.net.SocketAddress

import org.scooter.channel.{ Handler, Initializer }
import org.scooter.protocol.{ Command, Reply, Request }

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
    new Connection(configure(address).connect.sync.channel)
  }

  /**
   * Configure the connection via Netty bootstrap.
   *
   * @address The remote ScoketAddress.
   *
   * @todo Fix ChannelOptions to use TCP_NODELAY.
   *
   * @return The Boostrap.
   */
  private def configure(address: SocketAddress) = {
    (new Bootstrap).
      group(new NioEventLoopGroup).
      channel(new NioSocketChannel).
      handler(new Initializer).
      remoteAddress(address)
  }
}

/**
 * The Connection represents a socket connection from the driver to the
 * database server.
 *
 * @param channel The SocketChannel used in the Connection.
 */
class Connection protected[scooter](channel: Channel) {

  /**
   * Write the Command to the socket.
   *
   * @param command The Command to write.
   *
   * @return The ChannelFuture.
   */
  protected[scooter] def send(command: Command): ChannelFuture = {
    channel.write(command)
  }

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
  protected[scooter] def send(request: Request): Reply = {
    channel.write(request)
    handler.reply(request.id)
  }

  /**
   * Get the scooter io handler, the last object in the pipeline.
   *
   * @return The Handler.
   */
  private def handler: Handler = {
    channel.pipeline.last.asInstanceOf[Handler]
  }
}
