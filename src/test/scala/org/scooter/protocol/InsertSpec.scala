package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class InsertSpec extends Spec {

  "Insert#serialize" should {

    val insert = new Insert(headerValue, "scooter_test.users", Array(documentValue))
    val buffer = dynamicBuffer(55)

    "add the document to the buffer" in {
      insert.serialize(buffer)
      buffer.array must beEqualTo(dumpedInsert)
    }
  }
}
