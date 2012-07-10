package org.mongoid.scooter.bson

object Implications {

  implicit def wrapString(string: String) : StringWrapper = new StringWrapper(string)
}
