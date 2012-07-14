package org.mongoid.scooter.protocol

import org.mongoid.scooter.Collection

import java.nio.ByteBuffer

object Insert {

  def apply(collection: Collection, documents: Array[Map[String, Any]]) = {
    new Insert(collection.fullName, documents)
  }
}

class Insert(name: String, documents: Array[Map[String, Any]]) extends Message {

  def serialize(buffer: ByteBuffer) = {}
}
