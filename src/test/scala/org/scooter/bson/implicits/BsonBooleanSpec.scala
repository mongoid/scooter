package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Serialization._
import org.scooter.bson.Document

import org.scooter.spec.Spec

class BsonBooleanSpec extends Spec {

  "BsonBoolean#bsonWrite" should {

    "when the boolean is true" in {

      val buffer = dynamicBuffer(5)
      val wrapper = new BsonBoolean(true)

      "serialize the boolean to the buffer" in {
        wrapper.bsonWrite(buffer, field)
        buffer.array must beEqualTo(dumpedTrue)
      }
    }

    "when the boolean is false" in {

      val buffer = dynamicBuffer(5)
      val wrapper = new BsonBoolean(false)

      "serialize the boolean to the buffer" in {
        wrapper.bsonWrite(buffer, field)
        buffer.array must beEqualTo(dumpedFalse)
      }
    }
  }

  "Boolean#bsonWrite" should {

    "when the boolean is true" in {

      val buffer = dynamicBuffer(5)

      "serialize the boolean to the buffer" in {
        true.bsonWrite(buffer, field)
        buffer.array must beEqualTo(dumpedTrue)
      }
    }

    "when the boolean is false" in {

      val buffer = dynamicBuffer(5)

      "serialize the boolean to the buffer" in {
        false.bsonWrite(buffer, field)
        buffer.array must beEqualTo(dumpedFalse)
      }
    }
  }

  "BsonBoolean.bsonRead" should {

    "when the boolean is true" in {

      val buffer = dynamicBuffer(4)
      val doc = new Document

      "add the boolean true and key to the map" in {
        buffer.writeBytes(loadedTrue)
        BsonBoolean.bsonRead(buffer, doc)
        doc must beEqualTo(Document(field -> true))
      }
    }

    "when the boolean is false" in {

      val buffer = dynamicBuffer(4)
      val doc = new Document

      "add the boolean false and key to the map" in {
        buffer.writeBytes(loadedFalse)
        BsonBoolean.bsonRead(buffer, doc)
        doc must beEqualTo(Document(field -> false))
      }
    }
  }
}
