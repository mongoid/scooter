package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonDoubleSpec extends Spec {

  "BsonDouble#bsonWrite" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)
    val wrapper = new BsonDouble(doubleValue)

    "encode the double to the buffer" in {
      wrapper.bsonWrite(buff, field)
      buff.array must beEqualTo(dumpedDouble)
    }
  }

  "Double#bsonWrite" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)

    "encode the double to the buffer" in {
      doubleValue.bsonWrite(buff, field)
      buff.array must beEqualTo(dumpedDouble)
    }
  }

  "BsonDouble.bsonRead" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)
    val doc = new Document

    "add the key and double to the map" in {
      buff.writeBytes(loadedDouble)
      BsonDouble.bsonRead(buff, doc)
      doc must beEqualTo(Document(field -> doubleValue))
    }
  }
}
