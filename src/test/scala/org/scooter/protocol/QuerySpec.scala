package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.spec.Spec

class QuerySpec extends Spec {

  "Query#serialize" should {

    "when the query is not empty" in {

      val buff = buffer(63).order(LITTLE_ENDIAN)
      val query = new Query(queryHeaderValue, "scooter_test.users", documentValue, 0, 0)

      "serializes the message with the query document" in {
        query.serialize(buff)
        buff.array must beEqualTo(dumpedQuery)
      }
    }
  }
}
