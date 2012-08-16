package org.scooter.protocol

import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class QuerySpec extends Spec {

  "Query#serialize" should {

    "when the query is not empty" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 63)
      val query = new Query(queryHeaderValue, "scooter_test.users", documentValue)

      "serializes the message with the query document" in {
        query.serialize(buffer)
        buffer.array must beEqualTo(dumpedQuery)
      }
    }
  }
}
