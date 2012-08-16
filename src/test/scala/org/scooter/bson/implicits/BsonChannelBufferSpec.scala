import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.implicits.BsonChannelBuffer
import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonChannelBufferSpec extends Spec {

  "BsonChannelBuffer#readCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 3)
    val wrapper = new BsonChannelBuffer(buffer)

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

  "BsonChannelBuffer#readString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 7)
    val wrapper = new BsonChannelBuffer(buffer)

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

  "BsonChannelBuffer#writeCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 3)
    val wrapper = new BsonChannelBuffer(buffer)

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

  "BsonChannelBuffer#writeInts" should {

    val bytes = Array[Byte](1, 0, 0, 0, 2, 0, 0, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)
    val wrapper = new BsonChannelBuffer(buffer)

    "write each int to the buffer in order" in {
      wrapper.writeInts(1, 2)
      buffer.array must beEqualTo(bytes)
    }
  }

  "ChannelBuffer#writeInts" should {

    val bytes = Array[Byte](1, 0, 0, 0, 2, 0, 0, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)

    "write each int to the buffer in order" in {
      buffer.writeInts(1, 2)
      buffer.array must beEqualTo(bytes)
    }
  }

  "BsonChannelBuffer#writeString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 7)
    val wrapper = new BsonChannelBuffer(buffer)

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
