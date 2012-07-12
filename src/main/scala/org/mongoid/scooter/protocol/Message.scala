package org.mongoid.scooter.protocol

import java.nio.ByteBuffer

trait Message {

  def serialize(buffer: ByteBuffer) : Unit
}
