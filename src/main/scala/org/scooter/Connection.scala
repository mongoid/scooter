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
  def apply(address: SocketAddress) = new Connection(configure(address))

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
 * @param boostrap The Bootstrap used for the connection.
 */
class Connection(bootstrap: Bootstrap) {

  /**
   * Write the Command to the socket.
   *
   * @param command The Command to write.
   *
   * @return The ChannelFuture.
   */
  def send(command: Command): ChannelFuture = channel.write(command)

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
  def send(request: Request): Reply = {
    channel.write(request)
    handler.reply(request.id)
  }

  /**
   * Shutdown the Connection - will perform all the necessary Netty magic
   * to terminate all connections and threads.
   */
  def shutdown = {
    channel.disconnect.sync
    bootstrap.shutdown
  }

  /**
   * Get the channel from the bootstrap.
   *
   * @return The Channel.
   */
  private val channel = bootstrap.connect.sync.channel

  /**
   * Get the scooter io handler, the last object in the pipeline.
   *
   * @return The Handler.
   */
  private def handler: Handler = {
    channel.pipeline.last.asInstanceOf[Handler]
  }
}
