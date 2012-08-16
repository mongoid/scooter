import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.implicits.BsonBoolean
import org.scooter.bson.Serialization._
import org.scooter.bson.Document

import org.scooter.spec.Data

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class BsonBooleanSpec extends Specification with Data {

  "BsonBoolean#bsonDump" should {

    "when the boolean is true" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 5)
      val wrapper = new BsonBoolean(true)

      "serialize the boolean to the buffer" in {
        wrapper.bsonDump(buffer, field)
        buffer.array must beEqualTo(dumpedTrue)
      }
    }

    "when the boolean is false" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 5)
      val wrapper = new BsonBoolean(false)

      "serialize the boolean to the buffer" in {
        wrapper.bsonDump(buffer, field)
        buffer.array must beEqualTo(dumpedFalse)
      }
    }
  }

  "Boolean#bsonDump" should {

    "when the boolean is true" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 5)

      "serialize the boolean to the buffer" in {
        true.bsonDump(buffer, field)
        buffer.array must beEqualTo(dumpedTrue)
      }
    }

    "when the boolean is false" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 5)

      "serialize the boolean to the buffer" in {
        false.bsonDump(buffer, field)
        buffer.array must beEqualTo(dumpedFalse)
      }
    }
  }

  "BsonBoolean.bsonLoad" should {

    "when the boolean is true" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 4)
      val doc = new Document

      "add the boolean true and key to the map" in {
        buffer.writeBytes(loadedTrue)
        BsonBoolean.bsonLoad(buffer, doc)
        doc must beEqualTo(Document(field -> true))
      }
    }

    "when the boolean is false" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 4)
      val doc = new Document

      "add the boolean false and key to the map" in {
        buffer.writeBytes(loadedFalse)
        BsonBoolean.bsonLoad(buffer, doc)
        doc must beEqualTo(Document(field -> false))
      }
    }
  }
}
