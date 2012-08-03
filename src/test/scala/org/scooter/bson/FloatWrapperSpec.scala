import java.nio.ByteOrder
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._
import org.scooter.bson.FloatWrapper
import org.scooter.bson.Conversions._
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class FloatWrapperSpec extends Specification {

  "FloatWrapper#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val wrapper = new FloatWrapper(value)

    "serialize the float to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Float#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)

    "serialize the float to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  def value = 1.123f

  trait scope extends Scope {

    val key = "hi"
    val bytes = Array[Byte](1, 104, 105, 0, 0, 0, 0, -32, -50, -9, -15, 63)
  }
}
