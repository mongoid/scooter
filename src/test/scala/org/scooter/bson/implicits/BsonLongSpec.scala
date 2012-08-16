import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonLong
import org.scooter.bson.Serialization._

import org.scooter.spec.Data

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class BsonLongSpec extends Specification with Data {

  "BsonLong#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val wrapper = new BsonLong(longValue)

    "serialize the long to the buffer" in {
      wrapper.bsonDump(buffer, field)
      buffer.array must beEqualTo(dumpedLong)
    }
  }

  "Long#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)

    "serialize the long to the buffer" in {
      longValue.bsonDump(buffer, field)
      buffer.array must beEqualTo(dumpedLong)
    }
  }

  "BsonLong.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val doc = new Document

    "adds the key and the long to the doc" in {
      buffer.writeBytes(loadedLong)
      BsonLong.bsonLoad(buffer, doc)
      doc must beEqualTo(Document(field -> longValue))
    }
  }
}
