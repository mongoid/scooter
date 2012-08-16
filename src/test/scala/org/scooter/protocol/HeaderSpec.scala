import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Serialization._
import org.scooter.protocol.Header

import org.scooter.spec.Data

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class HeaderSpec extends Specification with Data {

  "Header#serialize" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 16)

    "adds the header as 32 bit integers" in {
      headerValue.serialize(buffer)
      buffer.array must beEqualTo(dumpedHeader)
    }
  }
}
