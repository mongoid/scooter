import java.nio.ByteOrder
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._
import org.scooter.bson.FloatWrapper
import org.scooter.bson.Conversions._
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import scala.collection.mutable.HashMap

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

  "FloatWrapper.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val map = new HashMap[String, Any]

    "add the key and float to the map" in new scope {
      buffer.writeBytes(bytes)
      buffer.readByte
      FloatWrapper.bsonLoad(buffer, map)
      map must beEqualTo(HashMap("hi" -> value))
    }
  }

  def value = 1.123f

  trait scope extends Scope {

    val key = "hi"
    val bytes = Array[Byte](1, 104, 105, 0, 0, 0, 0, -32, -50, -9, -15, 63)
  }
}
