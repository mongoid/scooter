import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Serialization._
import org.scooter.protocol.Header

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class HeaderSpec extends Specification {

  "Header#serialize" should {

    val bytes = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 16)

    "add the header as 32 bit integers" in new scope {
      header.serialize(buffer)
      buffer.array must beEqualTo(bytes)
    }
  }

  trait scope extends Scope {

    val header = Header(0, 0, 0, 2002)
  }
}
