package org.scooter.bson

import io.netty.buffer.Unpooled._

import org.scooter.spec.Spec

class MaxKeySpec extends Spec {

  "MaxKey#write" should {

    val maxKey = new MaxKey
    val buff = buffer(4).order(LITTLE_ENDIAN)

    "encode the min key to the buffer" in {
      maxKey.write(buff, field)
      buff.array must beEqualTo(dumpedMaxKey)
    }
  }

  "MaxKey.read" should {

    val buff = buffer(3).order(LITTLE_ENDIAN)
    val doc = new Document

    "add the boolean false and key to the map" in {
      buff.writeBytes(loadedMaxKey)
      MaxKey.read(buff, doc)
      doc must beEqualTo(Document(field -> new MaxKey))
    }
  }
}
