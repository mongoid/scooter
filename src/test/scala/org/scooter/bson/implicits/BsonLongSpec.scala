package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonLongSpec extends Spec {

  "BsonLong#bsonWrite" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)
    val wrapper = new BsonLong(longValue)

    "serialize the long to the buffer" in {
      wrapper.bsonWrite(buff, field)
      buff.array must beEqualTo(dumpedLong)
    }
  }

  "Long#bsonWrite" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)

    "serialize the long to the buffer" in {
      longValue.bsonWrite(buff, field)
      buff.array must beEqualTo(dumpedLong)
    }
  }

  "BsonLong.bsonRead" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)
    val doc = new Document

    "adds the key and the long to the doc" in {
      buff.writeBytes(loadedLong)
      BsonLong.bsonRead(buff, doc)
      doc must beEqualTo(Document(field -> longValue))
    }
  }
}
