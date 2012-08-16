import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonDouble
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonDoubleSpec extends Spec {

  "BsonDouble#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val wrapper = new BsonDouble(doubleValue)

    "serialize the double to the buffer" in {
      wrapper.bsonDump(buffer, field)
      buffer.array must beEqualTo(dumpedDouble)
    }
  }

  "Double#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)

    "serialize the double to the buffer" in {
      doubleValue.bsonDump(buffer, field)
      buffer.array must beEqualTo(dumpedDouble)
    }
  }

  "BsonDouble.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val doc = new Document

    "add the key and double to the map" in {
      buffer.writeBytes(loadedDouble)
      BsonDouble.bsonLoad(buffer, doc)
      doc must beEqualTo(Document(field -> doubleValue))
    }
  }
}
