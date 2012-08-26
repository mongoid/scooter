package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class InsertSpec extends Spec {

  "Insert#encode" should {

    val insert = new Insert(headerValue, "scooter_test.users", Array(documentValue))
    val buff = buffer(55).order(LITTLE_ENDIAN)

    "add the document to the buffer" in {
      insert.encode(buff)
      buff.array must beEqualTo(dumpedInsert)
    }
  }
}
