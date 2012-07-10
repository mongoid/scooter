package org.mongoid.scooter.bson

class String(wrapped: String) {

  def bsonDump = Unit
  def bsonLoad = Unit

  implicit def bsonifyString(string: String) = new String(string)
}
