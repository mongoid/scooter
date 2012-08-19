package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.spec.Spec

class QuerySpec extends Spec {

  "Query#serialize" should {

    "when the query is not empty" in {

      val buffer = dynamicBuffer(63)
      val query = new Query(queryHeaderValue, "scooter_test.users", documentValue)

      "serializes the message with the query document" in {
        query.serialize(buffer)
        buffer.array must beEqualTo(dumpedQuery)
      }
    }
  }
}
