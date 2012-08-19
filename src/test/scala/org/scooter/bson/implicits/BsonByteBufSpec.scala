package org.scooter.bson.implicits

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled._

import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonByteBufSpec extends Spec {

  "BsonByteBuf#readCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(3)
    val wrapper = new BsonByteBuf(buffer)

    "return the string from the buffer" in {
      buffer.writeBytes(bytes)
      wrapper.readCString must beEqualTo("hi")
    }
  }

  "ByteBuf#readCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(3)

    "return the string from the buffer" in {
      buffer.writeBytes(bytes)
      buffer.readCString must beEqualTo("hi")
    }
  }

  "BsonByteBuf#readString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(7)
    val wrapper = new BsonByteBuf(buffer)

    "return the string from the buffer" in {
      buffer.writeBytes(bytes)
      wrapper.readString must beEqualTo("ya")
    }
  }

  "ByteBuf#readString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(7)

    "return the string from the buffer" in {
      buffer.writeBytes(bytes)
      buffer.readString must beEqualTo("ya")
    }
  }

  "BsonByteBuf#writeCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(3)
    val wrapper = new BsonByteBuf(buffer)

    "return the string from the buffer" in {
      wrapper.writeCString("hi")
      buffer.array must beEqualTo(bytes)
    }
  }

  "ByteBuf#writeCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buffer = dynamicBuffer(3)

    "return the string from the buffer" in {
      buffer.writeCString("hi")
      buffer.array must beEqualTo(bytes)
    }
  }

  "BsonByteBuf#writeInts" should {

    val bytes = Array[Byte](1, 0, 0, 0, 2, 0, 0, 0)
    val buffer = dynamicBuffer(8)
    val wrapper = new BsonByteBuf(buffer)

    "write each int to the buffer in order" in {
      wrapper.writeInts(1, 2)
      buffer.array must beEqualTo(bytes)
    }
  }

  "ByteBuf#writeInts" should {

    val bytes = Array[Byte](1, 0, 0, 0, 2, 0, 0, 0)
    val buffer = dynamicBuffer(8)

    "write each int to the buffer in order" in {
      buffer.writeInts(1, 2)
      buffer.array must beEqualTo(bytes)
    }
  }

  "BsonByteBuf#writeString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(7)
    val wrapper = new BsonByteBuf(buffer)

    "return the string from the buffer" in {
      wrapper.writeString("ya")
      buffer.array must beEqualTo(bytes)
    }
  }

  "ByteBuf#writeString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buffer = dynamicBuffer(7)

    "return the string from the buffer" in {
      buffer.writeString("ya")
      buffer.array must beEqualTo(bytes)
    }
  }
}
