import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class DocumentSpec extends Specification {

  "Document.apply" should {

    val document = Document("first" -> 1, "second" -> "test")

    "create a new document with the first pair" in {
      document must havePair("first" -> 1)
    }

    "create a new document with the second pair" in {
      document must havePair("second" -> "test")
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

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 16)
    val document = Document("hi" -> "ya")

    "deserialize the bytes into a hash map" in new scope {
      // buffer.put(bytes)
      // Document.bsonLoad(buffer, doc) must beEqualTo(hash)
    }
  }

  trait scope extends Scope {

    val bytes = Array[Byte](16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)
  }
}
