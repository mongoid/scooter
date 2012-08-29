package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class UpdateSpec extends Spec {

  "Update#encode" should {

    val update = new Update(
      updateHeaderValue,
      "scooter_test.users",
      selectorValue,
      updateValue
    )

    val buff = buffer(86).order(LITTLE_ENDIAN)

    "add the document to the buffer" in {
      update.encode(buff)
      buff.array must beEqualTo(dumpedUpdate)
    }
  }
}
