import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonLong
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonLongSpec extends Spec {

  "BsonLong#bsonWrite" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val wrapper = new BsonLong(longValue)

    "serialize the long to the buffer" in {
      wrapper.bsonWrite(buffer, field)
      buffer.array must beEqualTo(dumpedLong)
    }
  }

  "Long#bsonWrite" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)

    "serialize the long to the buffer" in {
      longValue.bsonWrite(buffer, field)
      buffer.array must beEqualTo(dumpedLong)
    }
  }

  "BsonLong.bsonRead" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val doc = new Document

    "adds the key and the long to the doc" in {
      buffer.writeBytes(loadedLong)
      BsonLong.bsonRead(buffer, doc)
      doc must beEqualTo(Document(field -> longValue))
    }
  }
}
