package org.scooter.protocol

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

/**
 * Represents a message that is sent to the database that does not get
 * a reply back.
 *
 * @param code The operation code.
 */
abstract class Command(code: Int) extends Message(code) with Serializable
