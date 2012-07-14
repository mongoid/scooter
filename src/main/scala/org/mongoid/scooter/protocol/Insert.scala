package org.mongoid.scooter.protocol

import java.nio.ByteBuffer

import org.mongoid.scooter.Collection
import org.mongoid.scooter.bson._
import org.mongoid.scooter.bson.Conversions._

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
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 * @param name The full name of the Collection.
 * @param documents The documents to insert.
 */
class Insert(name: String, documents: Array[_<:Map[String, Any]]) extends Message {

  def serialize(buffer: ByteBuffer) = {
    documents.foreach {
      doc => doc.foreach {
        case (key: String, value: String) => value.bsonDump(buffer, key)
      }
    }
  }
}
