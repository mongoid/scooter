package org.scooter.protocol

/**
 * Represents a message that is sent to the database that does not get
 * a reply back.
 *
 * @param header The Header.
 * @param code The operation code.
 */
abstract class Command(header: Header, code: Int)
  extends Message(header, code) with Encodable
