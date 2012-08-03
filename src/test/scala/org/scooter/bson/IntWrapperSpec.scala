import java.nio.ByteOrder
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._
import org.scooter.bson.IntWrapper
import org.scooter.bson.Conversions._
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class IntWrapperSpec extends Specification {

  "IntWrapper#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)
    val wrapper = new IntWrapper(value)

    "serialize the int to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Int#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)

    "serialize the int to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  def value = 1

  trait scope extends Scope {

    val key = "hi"
    val bytes = Array[Byte](16, 104, 105, 0, 1, 0, 0, 0)
  }
}
