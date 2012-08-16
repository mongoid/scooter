import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits._
import org.scooter.bson.Serialization._

import org.scooter.spec.Data

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class DocumentSpec extends Specification with Data {

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

    "serializes the string to the buffer" in {
      documentValue.bsonDump(buffer)
      buffer.array must beEqualTo(dumpedDocument)
    }
  }

  "Document.bsonLoad" should {

    "when the document has a single pair" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 16)

      "deserialize the bytes into a hash map" in {
        buffer.writeBytes(dumpedDocument)
        Document.bsonLoad(buffer) must beEqualTo(documentValue)
      }
    }

    "when the document has multiple pairs" in {

      val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 27)

      "deserialize the bytes into a hash map" in {
        buffer.writeBytes(dumpedMultiDocument)
        Document.bsonLoad(buffer) must beEqualTo(multiDocumentValue)
      }
    }
  }
}
