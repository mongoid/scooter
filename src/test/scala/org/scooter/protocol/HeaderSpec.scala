package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class HeaderSpec extends Spec {

  "Header#serialize" should {

    val buffer = dynamicBuffer(16)

    "adds the header as 32 bit integers" in {
      headerValue.serialize(buffer)
      buffer.array must beEqualTo(dumpedHeader)
    }
  }
}
