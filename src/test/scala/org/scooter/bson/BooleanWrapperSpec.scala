import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.BooleanWrapper
import org.scooter.bson.Conversions._
import org.scooter.bson.Document

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class BooleanWrapperSpec extends Specification {

  "BooleanWrapper#bsonDump" should {

    "when the boolean is true" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 5)
      val bytes = Array[Byte](8, 104, 105, 0, 1)
      val wrapper = new BooleanWrapper(true)

      "serialize the boolean to the buffer" in new scope {
        wrapper.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }

    "when the boolean is false" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 5)
      val bytes = Array[Byte](8, 104, 105, 0, 0)
      val wrapper = new BooleanWrapper(false)

      "serialize the boolean to the buffer" in new scope {
        wrapper.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }
  }

  "Boolean#bsonDump" should {

    "when the boolean is true" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 5)
      val bytes = Array[Byte](8, 104, 105, 0, 1)

      "serialize the boolean to the buffer" in new scope {
        true.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }

    "when the boolean is false" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 5)
      val bytes = Array[Byte](8, 104, 105, 0, 0)

      "serialize the boolean to the buffer" in new scope {
        false.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }
  }

  "BooleanWrapper.bsonLoad" should {

    "when the boolean is true" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 4)
      val bytes = Array[Byte](104, 105, 0, 1)
      val doc = new Document

      "add the boolean true and key to the map" in new scope {
        buffer.writeBytes(bytes)
        BooleanWrapper.bsonLoad(buffer, doc)
        doc must beEqualTo(Document("hi" -> true))
      }
    }

    "when the boolean is false" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 4)
      val bytes = Array[Byte](104, 105, 0, 0)
      val doc = new Document

      "add the boolean false and key to the map" in new scope {
        buffer.writeBytes(bytes)
        BooleanWrapper.bsonLoad(buffer, doc)
        doc must beEqualTo(Document("hi" -> false))
      }
    }
  }

  trait scope extends Scope {

    val key = "hi"
  }
}
