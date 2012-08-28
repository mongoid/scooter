package org.scooter.protocol

import org.scooter.codec.Encodable

/**
 * Represents a message that is sent to the database that expects a reply.
 *
 * @param header The Header.
 * @param code The operation code.
 */
abstract class Request(header: Header, code: Int)
  extends Message(header, code) with Encodable {

  /**
   * Get the unique id of the request in the context of this deployment.
   *
   * @return The Int request id.
   */
  def id = header.request
}
