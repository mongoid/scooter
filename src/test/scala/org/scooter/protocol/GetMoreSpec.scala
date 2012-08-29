package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.spec.Spec

class GetMoreSpec extends Spec {

  "GetMore#encode" should {

    "when the getMore is not empty" in {

      val buff = buffer(51).order(LITTLE_ENDIAN)
      val getMore = new GetMore(getMoreHeaderValue, "scooter_test.users", 100, 123l)

      "encodes the message with the getMore document" in {
        getMore.encode(buff)
        buff.array must beEqualTo(dumpedGetMore)
      }
    }
  }
}
