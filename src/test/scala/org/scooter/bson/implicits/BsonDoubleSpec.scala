package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonDoubleSpec extends Spec {

  "BsonDouble#write" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)
    val wrapper = new BsonDouble(doubleValue)

    "encode the double to the buffer" in {
      wrapper.write(buff, field)
      buff.array must beEqualTo(dumpedDouble)
    }
  }

  "Double#write" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)

    "encode the double to the buffer" in {
      doubleValue.write(buff, field)
      buff.array must beEqualTo(dumpedDouble)
    }
  }

  "BsonDouble.read" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)
    val doc = new Document

    "add the key and double to the map" in {
      buff.writeBytes(loadedDouble)
      BsonDouble.read(buff, doc)
      doc must beEqualTo(Document(field -> doubleValue))
    }
  }
}
