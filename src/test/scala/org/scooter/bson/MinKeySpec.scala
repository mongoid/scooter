package org.scooter.bson

import io.netty.buffer.Unpooled._

import org.scooter.spec.Spec

class MinKeySpec extends Spec {

  "MinKey#write" should {

    val minKey = new MinKey
    val buff = buffer(4).order(LITTLE_ENDIAN)

    "encode the min key to the buffer" in {
      minKey.write(buff, field)
      buff.array must beEqualTo(dumpedMinKey)
    }
  }

  "MinKey.read" should {

    val buff = buffer(3).order(LITTLE_ENDIAN)
    val doc = new Document

    "add the boolean false and key to the map" in {
      buff.writeBytes(loadedMinKey)
      MinKey.read(buff, doc)
      doc must beEqualTo(Document(field -> new MinKey))
    }
  }
}
