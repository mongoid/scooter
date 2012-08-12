import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits._
import org.scooter.bson.Serialization._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class DocumentSpec extends Specification {

  "Document.apply" should {

    val document = Document("first" -> 1, "second" -> "test")

    "create a new document with the first pair" in {
      document must havePair("first" -> new BsonInt(1))
    }

    "create a new document with the second pair" in {
      document must havePair("second" -> new BsonString("test"))
    }
  }

  "Document#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 16)
    val document = Document("hi" -> "ya")

    "serializes the string to the buffer" in new scope {
      document.bsonDump(buffer)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Document.bsonLoad" should {

    "when the document has a single pair" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 16)
      val document = Document("hi" -> "ya")

      "deserialize the bytes into a hash map" in new scope {
        buffer.writeBytes(bytes)
        Document.bsonLoad(buffer) must beEqualTo(document)
      }
    }

    "when the document has multiple pairs" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 27)
      val document = Document("hi" -> "ya", "hj" -> "ya")

      "deserialize the bytes into a hash map" in new scope {
        buffer.writeBytes(multi)
        Document.bsonLoad(buffer) must beEqualTo(document)
      }
    }
  }

  trait scope extends Scope {

    val bytes = Array[Byte](16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)
    val multi = Array[Byte](27, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 2, 104, 106, 0, 3, 0, 0, 0, 121, 97, 0, 0)
  }
}
