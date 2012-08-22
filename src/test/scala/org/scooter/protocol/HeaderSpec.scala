package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class HeaderSpec extends Spec {

  "Header#serialize" should {

    val buff = buffer(16).order(LITTLE_ENDIAN)

    "adds the header as 32 bit integers" in {
      headerValue.serialize(buff)
      buff.array must beEqualTo(dumpedHeader)
    }
  }
}
