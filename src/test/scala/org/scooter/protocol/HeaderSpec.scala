package org.scooter.protocol

import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class HeaderSpec extends Spec {

  "Header#serialize" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 16)

    "adds the header as 32 bit integers" in {
      headerValue.serialize(buffer)
      buffer.array must beEqualTo(dumpedHeader)
    }
  }
}
