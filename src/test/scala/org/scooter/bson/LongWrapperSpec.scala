import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.LongWrapper
import org.scooter.bson.Serialization._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class LongWrapperSpec extends Specification {

  "LongWrapper#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val wrapper = new LongWrapper(value)

    "serialize the int to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Long#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)

    "serialize the int to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  def value = 1l

  trait scope extends Scope {

    val key = "hi"
    val bytes = Array[Byte](18, 104, 105, 0, 1, 0, 0, 0, 0, 0, 0, 0)
  }
}
