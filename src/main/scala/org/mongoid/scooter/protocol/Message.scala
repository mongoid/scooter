package org.mongoid.scooter.protocol

import java.nio.ByteBuffer

trait Message {

  def bsonSerialize(buffer: ByteBuffer) : Unit
}
