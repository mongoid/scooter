import java.nio.ByteOrder
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._
import org.scooter.bson.StringWrapper
import org.scooter.bson.Conversions._
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import scala.collection.mutable.HashMap

class StringWrapperSpec extends Specification {

  "StringWrapper#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 11)
    val wrapper = new StringWrapper(value)

    "serialize the string to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "String#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 11)

    "serialize the string to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "StringWrapper.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 11)
    var doc = new HashMap[String, Any]
    val wrapper = new StringWrapper(value)

    "load the key and value into the hash" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.readByte
      StringWrapper.bsonLoad(buffer, doc)
      doc must beEqualTo(HashMap("hi" -> "ya"))
    }
  }

  def value = "ya"

  trait scope extends Scope {

    val key = "hi"
    val bytes = Array[Byte](2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0)
  }
}
