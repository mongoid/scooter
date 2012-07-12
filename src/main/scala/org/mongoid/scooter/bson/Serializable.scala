package org.mongoid.scooter.bson

import java.nio.ByteBuffer

trait Serializable {

  def bsonSerialize(buffer: ByteBuffer) : Unit
}
