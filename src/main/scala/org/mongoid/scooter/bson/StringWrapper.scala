package org.mongoid.scooter.bson

import java.nio.ByteBuffer

object StringWrapper {

  implicit def wrap(target: String) : StringWrapper = new StringWrapper(target)
}

class StringWrapper(target: String) extends Serializable {

  def bsonSerialize(buffer: ByteBuffer) = {}
}
