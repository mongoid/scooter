package org.scooter.bson.implicits

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled._

import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonByteBufSpec extends Spec {

  "BsonByteBuf#readCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buff = buffer(3).order(LITTLE_ENDIAN)
    val wrapper = new BsonByteBuf(buff)

    "return the string from the buffer" in {
      buff.writeBytes(bytes)
      wrapper.readCString must beEqualTo("hi")
    }
  }

  "ByteBuf#readCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buff = buffer(3).order(LITTLE_ENDIAN)

    "return the string from the buffer" in {
      buff.writeBytes(bytes)
      buff.readCString must beEqualTo("hi")
    }
  }

  "BsonByteBuf#readString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buff = buffer(7).order(LITTLE_ENDIAN)
    val wrapper = new BsonByteBuf(buff)

    "return the string from the buffer" in {
      buff.writeBytes(bytes)
      wrapper.readString must beEqualTo("ya")
    }
  }

  "ByteBuf#readString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buff = buffer(7).order(LITTLE_ENDIAN)

    "return the string from the buffer" in {
      buff.writeBytes(bytes)
      buff.readString must beEqualTo("ya")
    }
  }

  "BsonByteBuf#writeCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buff = buffer(3).order(LITTLE_ENDIAN)
    val wrapper = new BsonByteBuf(buff)

    "return the string from the buffer" in {
      wrapper.writeCString("hi")
      buff.array must beEqualTo(bytes)
    }
  }

  "ByteBuf#writeCString" should {

    val bytes = Array[Byte](104, 105, 0)
    val buff = buffer(3).order(LITTLE_ENDIAN)

    "return the string from the buffer" in {
      buff.writeCString("hi")
      buff.array must beEqualTo(bytes)
    }
  }

  "BsonByteBuf#writeInts" should {

    val bytes = Array[Byte](1, 0, 0, 0, 2, 0, 0, 0)
    val buff = buffer(8).order(LITTLE_ENDIAN)
    val wrapper = new BsonByteBuf(buff)

    "write each int to the buffer in order" in {
      wrapper.writeInts(1, 2)
      buff.array must beEqualTo(bytes)
    }
  }

  "ByteBuf#writeInts" should {

    val bytes = Array[Byte](1, 0, 0, 0, 2, 0, 0, 0)
    val buff = buffer(8).order(LITTLE_ENDIAN)

    "write each int to the buffer in order" in {
      buff.writeInts(1, 2)
      buff.array must beEqualTo(bytes)
    }
  }

  "BsonByteBuf#writeString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buff = buffer(7).order(LITTLE_ENDIAN)
    val wrapper = new BsonByteBuf(buff)

    "return the string from the buffer" in {
      wrapper.writeString("ya")
      buff.array must beEqualTo(bytes)
    }
  }

  "ByteBuf#writeString" should {

    val bytes = Array[Byte](3, 0, 0, 0, 121, 97, 0)
    val buff = buffer(7).order(LITTLE_ENDIAN)

    "return the string from the buffer" in {
      buff.writeString("ya")
      buff.array must beEqualTo(bytes)
    }
  }
}
