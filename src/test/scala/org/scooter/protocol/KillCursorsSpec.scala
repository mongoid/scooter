package org.scooter.protocol

import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class KillCursorsSpec extends Spec {

  "KillCursors#serialize" should {

    val killCursors = new KillCursors(headerValue, cursorValues)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 40)

    "add the document to the buffer" in {
      killCursors.serialize(buffer)
      buffer.array must beEqualTo(dumpedKillCursors)
    }
  }
}
