package org.mongoid.scooter.bson

import java.nio.ByteBuffer

trait Serializable {

  def bsonDump(buffer: ByteBuffer, key: String) : Unit
}
