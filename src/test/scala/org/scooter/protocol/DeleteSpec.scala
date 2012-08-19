package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class DeleteSpec extends Spec {

  "Delete#serialize" should {

    val delete = new Delete(headerValue, "scooter_test.users", documentValue)
    val buffer = dynamicBuffer(59)

    "add the document to the buffer" in {
      delete.serialize(buffer)
      buffer.array must beEqualTo(dumpedDelete)
    }
  }
}
