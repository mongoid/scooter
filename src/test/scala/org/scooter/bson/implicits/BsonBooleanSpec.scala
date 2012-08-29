package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Serialization._
import org.scooter.bson.Document

import org.scooter.spec.Spec

class BsonBooleanSpec extends Spec {

  "BsonBoolean#write" should {

    "when the boolean is true" in {

      val buff = buffer(5).order(LITTLE_ENDIAN)
      val wrapper = new BsonBoolean(true)

      "encode the boolean to the buffer" in {
        wrapper.write(buff, field)
        buff.array must beEqualTo(dumpedTrue)
      }
    }

    "when the boolean is false" in {

      val buff = buffer(5).order(LITTLE_ENDIAN)
      val wrapper = new BsonBoolean(false)

      "encode the boolean to the buffer" in {
        wrapper.write(buff, field)
        buff.array must beEqualTo(dumpedFalse)
      }
    }
  }

  "Boolean#write" should {

    "when the boolean is true" in {

      val buff = buffer(5).order(LITTLE_ENDIAN)

      "encode the boolean to the buffer" in {
        true.write(buff, field)
        buff.array must beEqualTo(dumpedTrue)
      }
    }

    "when the boolean is false" in {

      val buff = buffer(5).order(LITTLE_ENDIAN)

      "encode the boolean to the buffer" in {
        false.write(buff, field)
        buff.array must beEqualTo(dumpedFalse)
      }
    }
  }

  "BsonBoolean.bsonRead" should {

    "when the boolean is true" in {

      val buff = buffer(4).order(LITTLE_ENDIAN)
      val doc = new Document

      "add the boolean true and key to the map" in {
        buff.writeBytes(loadedTrue)
        BsonBoolean.bsonRead(buff, doc)
        doc must beEqualTo(Document(field -> true))
      }
    }

    "when the boolean is false" in {

      val buff = buffer(4).order(LITTLE_ENDIAN)
      val doc = new Document

      "add the boolean false and key to the map" in {
        buff.writeBytes(loadedFalse)
        BsonBoolean.bsonRead(buff, doc)
        doc must beEqualTo(Document(field -> false))
      }
    }
  }
}
