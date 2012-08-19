package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class ReplySpec extends Spec {

  "Reply.deserialize" should {

    "when the buffer has more than one document" in {

      val buffer = dynamicBuffer(52)
      buffer.writeBytes(dumpedReply)
      val reply = Reply.deserialize(buffer)

      "returns a reply with the first document" in {
        reply.documents(0) must beEqualTo(Document("hi" -> "ya"))
      }

      "returns a reply with the second document" in {
        reply.documents(1) must beEqualTo(Document("hj" -> "ya"))
      }
    }
  }
}
