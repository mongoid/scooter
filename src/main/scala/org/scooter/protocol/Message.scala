package org.scooter.protocol

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

/**
 * All Messages sent/received to/from the database inherit from this class.
 *
 * @param code The operation code.
 */
abstract class Message(code: Int) {}
