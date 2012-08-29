package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonLongSpec extends Spec {

  "BsonLong#write" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)
    val wrapper = new BsonLong(longValue)

    "encode the long to the buffer" in {
      wrapper.write(buff, field)
      buff.array must beEqualTo(dumpedLong)
    }
  }

  "Long#write" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)

    "encode the long to the buffer" in {
      longValue.write(buff, field)
      buff.array must beEqualTo(dumpedLong)
    }
  }

  "BsonLong.read" should {

    val buff = buffer(12).order(LITTLE_ENDIAN)
    val doc = new Document

    "adds the key and the long to the doc" in {
      buff.writeBytes(loadedLong)
      BsonLong.read(buff, doc)
      doc must beEqualTo(Document(field -> longValue))
    }
  }
}
