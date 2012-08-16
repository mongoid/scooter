package org.scooter.protocol

import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

import org.specs2.specification.Scope

class QuerySpec extends Spec {

  "Query#serialize" should {

    "when the query is not empty" in {

      val selector = Document("hi" -> "ya")

      "serializes the message with the query document" in new scope {
        val query = new Query(header, "scooter_test.users", selector)
        query.serialize(buffer)
        buffer.array must beEqualTo(bytes)
      }
    }
  }

  trait scope extends Scope {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 63)
    val header = Header(0, 0, 2004)
    val headerBytes = Array[Byte](63, 0, 0, 0, header.request.toByte, 0, 0, 0, 0, 0, 0, 0, -44, 7, 0, 0)
    val vector = Array[Byte](0, 0, 0, 0)
    val collection = Array[Byte](115, 99, 111, 111, 116, 101, 114, 95, 116, 101, 115, 116, 46, 117, 115, 101, 114, 115, 0)
    val options = Array[Byte](0, 0, 0, 0, 0, 0, 0, 0)
    val selectorBytes  = Array[Byte](16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)

    val bytes = headerBytes ++ vector ++ collection ++ options ++ selectorBytes
  }
}
