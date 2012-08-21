package org.scooter

import io.netty.channel.Channel

import java.net.SocketAddress

import org.scooter.channel.Config.bootstrap
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
    new Connection(bootstrap(address))
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
  protected[scooter] def send(command: Command) = {
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
  protected[scooter] def send(request: Request) = {
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
