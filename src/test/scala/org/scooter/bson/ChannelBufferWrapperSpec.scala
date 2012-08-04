import java.nio.ByteOrder
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._
import org.scooter.bson.ChannelBufferWrapper
import org.scooter.bson.Conversions._
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class ChannelBufferWrapperSpec extends Specification {

  "ChannelBufferWrapper#readCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 3)
    val wrapper = new ChannelBufferWrapper(buffer)

    "return the string from the buffer" in {
      buffer.writeBytes(bytes)
      wrapper.readCString must beEqualTo("hi")
    }
  }

  "ChannelBuffer#readCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 3)

    "return the string from the buffer" in {
      buffer.writeBytes(bytes)
      buffer.readCString must beEqualTo("hi")
    }
  }

  "ChannelBufferWrapper#readString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 7)
    val wrapper = new ChannelBufferWrapper(buffer)

    "return the string from the buffer" in {
      buffer.writeBytes(bytes)
      wrapper.readString must beEqualTo("ya")
    }
  }

  "ChannelBuffer#readString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 7)

    "return the string from the buffer" in {
      buffer.writeBytes(bytes)
      buffer.readString must beEqualTo("ya")
    }
  }

  "ChannelBufferWrapper#writeCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 3)
    val wrapper = new ChannelBufferWrapper(buffer)

    "return the string from the buffer" in {
      wrapper.writeCString("hi")
      buffer.array must beEqualTo(bytes)
    }
  }

  "ChannelBuffer#writeCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 3)

    "return the string from the buffer" in {
      buffer.writeCString("hi")
      buffer.array must beEqualTo(bytes)
    }
  }

  "ChannelBufferWrapper#writeString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 7)
    val wrapper = new ChannelBufferWrapper(buffer)

    "return the string from the buffer" in {
      wrapper.writeString("ya")
      buffer.array must beEqualTo(bytes)
    }
  }

  "ChannelBuffer#writeString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 7)

    "return the string from the buffer" in {
      buffer.writeString("ya")
      buffer.array must beEqualTo(bytes)
    }
  }
}
