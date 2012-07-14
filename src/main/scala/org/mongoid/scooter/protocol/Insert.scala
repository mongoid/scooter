package org.mongoid.scooter.protocol

import java.nio.ByteBuffer

import org.mongoid.scooter.Collection

/**
 * Companion object for the Insert class.
 */
object Insert {

  /**
   * Instantiate a new Insert message.
   *
   * @param collection The Collection to insert into.
   * @param documents The documents to insert.
   * @return The Insert message.
   */
  def apply(collection: Collection, documents: Array[_<:Map[String, Any]]) = {
    new Insert(collection.fullName, documents)
  }
}

/**
 * This class encapsulates behaviour for the OP_INSERT command in the
 * MongoDB Wire Protocol.
 *
 * See: http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param name The full name of the Collection.
 * @param documents The documents to insert.
 */
class Insert(name: String, documents: Array[_<:Map[String, Any]]) extends Message {

  def serialize(buffer: ByteBuffer) = {}
}
