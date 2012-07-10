package org.mongoid.scooter.bson

trait Serializable {

  def bsonDump: Unit
}
