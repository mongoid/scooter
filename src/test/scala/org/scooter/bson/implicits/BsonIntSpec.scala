import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonInt
import org.scooter.bson.Serialization._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class BsonIntSpec extends Specification {

  "BsonInt#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)
    val wrapper = new BsonInt(value)

    "serialize the int to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Int#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)

    "serialize the int to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "BsonInt.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)
    val doc = new Document

    "adds the key and the int to the doc" in new scope {
      buffer.writeBytes(bytes)
      buffer.readByte
      BsonInt.bsonLoad(buffer, doc)
      doc must beEqualTo(Document("hi" -> 1))
    }
  }

  def value = 1

  trait scope extends Scope {

    val key = "hi"
    val bytes = Array[Byte](16, 104, 105, 0, 1, 0, 0, 0)
  }
}
