package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonDoubleSpec extends Spec {

  "BsonDouble#bsonWrite" should {

    val buffer = dynamicBuffer(12)
    val wrapper = new BsonDouble(doubleValue)

    "serialize the double to the buffer" in {
      wrapper.bsonWrite(buffer, field)
      buffer.array must beEqualTo(dumpedDouble)
    }
  }

  "Double#bsonWrite" should {

    val buffer = dynamicBuffer(12)

    "serialize the double to the buffer" in {
      doubleValue.bsonWrite(buffer, field)
      buffer.array must beEqualTo(dumpedDouble)
    }
  }

  "BsonDouble.bsonRead" should {

    val buffer = dynamicBuffer(12)
    val doc = new Document

    "add the key and double to the map" in {
      buffer.writeBytes(loadedDouble)
      BsonDouble.bsonRead(buffer, doc)
      doc must beEqualTo(Document(field -> doubleValue))
    }
  }
}
