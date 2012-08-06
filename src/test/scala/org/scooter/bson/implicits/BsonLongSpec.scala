import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonLong
import org.scooter.bson.Serialization._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class BsonLongSpec extends Specification {

  "BsonLong#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val wrapper = new BsonLong(value)

    "serialize the int to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Long#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)

    "serialize the int to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "BsonLong.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 12)
    val doc = new Document

    "adds the key and the int to the doc" in new scope {
      buffer.writeBytes(bytes)
      buffer.readByte
      BsonLong.bsonLoad(buffer, doc)
      doc must beEqualTo(Document("hi" -> value))
    }
  }

  def value = 1l

  trait scope extends Scope {

    val key = "hi"
    val bytes = Array[Byte](18, 104, 105, 0, 1, 0, 0, 0, 0, 0, 0, 0)
  }
}
