package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class KillCursorsSpec extends Spec {

  "KillCursors#serialize" should {

    val killCursors = new KillCursors(headerValue, cursorValues)
    val buffer = dynamicBuffer(40)

    "add the document to the buffer" in {
      killCursors.serialize(buffer)
      buffer.array must beEqualTo(dumpedKillCursors)
    }
  }
}
