import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonDouble
import org.scooter.bson.Serialization._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class BsonDoubleSpec extends Specification {

  "BsonDouble#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val wrapper = new BsonDouble(value)

    "serialize the double to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Double#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)

    "serialize the double to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "BsonDouble.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val doc = new Document

    "add the key and double to the map" in new scope {
      buffer.writeBytes(bytes)
      buffer.readByte
      BsonDouble.bsonLoad(buffer, doc)
      doc must beEqualTo(Document("hi" -> value))
    }
  }

  def value = 1.123d

  trait scope extends Scope {

    val key = "hi"
    val bytes = Array[Byte](1, 104, 105, 0, 43, -121, 22, -39, -50, -9, -15, 63)
  }
}
