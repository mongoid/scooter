package org.mongoid.scooter.bson

object StringWrapper {

  implicit def wrapString(string: String) : StringWrapper = new StringWrapper(string)
}

class StringWrapper(target: String) extends Serializable {

  def bsonDump: Unit = {}
}
