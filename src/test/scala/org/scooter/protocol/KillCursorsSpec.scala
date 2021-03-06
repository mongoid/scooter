package org.scooter.protocol

import io.netty.buffer.Unpooled._

import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class KillCursorsSpec extends Spec {

  "KillCursors#encode" should {

    val killCursors = new KillCursors(headerValue, cursorValues)
    val buff = buffer(40).order(LITTLE_ENDIAN)

    "add the document to the buffer" in {
      killCursors.encode(buff)
      buff.array must beEqualTo(dumpedKillCursors)
    }
  }
}
