package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class DeleteSpec extends Spec {

  "Delete#encode" should {

    val delete = new Delete(headerValue, "scooter_test.users", documentValue)
    val buff = buffer(59).order(LITTLE_ENDIAN)

    "add the document to the buffer" in {
      delete.encode(buff)
      buff.array must beEqualTo(dumpedDelete)
    }
  }
}
