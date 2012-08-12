package org.scooter.protocol

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

/**
 * Represents a message that is sent to the database that expects a reply.
 *
 * @param code The operation code.
 */
abstract class Request(head: Header, code: Int) extends Message(head, code)
  with Serializable
