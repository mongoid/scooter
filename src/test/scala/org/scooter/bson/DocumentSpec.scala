package org.scooter.bson

import io.netty.buffer.Unpooled._

import org.scooter.bson.implicits._
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class DocumentSpec extends Spec {

  "Document.apply" should {

    val document = Document("first" -> 1, "second" -> "test")

    "create a new document with the first pair" in {
      document must havePair("first" -> new BsonInt(1))
    }

    "create a new document with the second pair" in {
      document must havePair("second" -> new BsonString("test"))
    }
  }

  "Document#encode" should {

    val buff = buffer(16).order(LITTLE_ENDIAN)

    "encodes the string to the buffer" in {
      documentValue.encode(buff)
      buff.array must beEqualTo(dumpedDocument)
    }
  }

  "Document.decode" should {

    "when the document has a single pair" in {

      val buff = buffer(16).order(LITTLE_ENDIAN)

      "decode the bytes into a hash map" in {
        buff.writeBytes(dumpedDocument)
        Document.decode(buff) must beEqualTo(documentValue)
      }
    }

    "when the document has multiple pairs" in {

      val buff = buffer(27).order(LITTLE_ENDIAN)

      "decode the bytes into a hash map" in {
        buff.writeBytes(dumpedMultiDocument)
        Document.decode(buff) must beEqualTo(multiDocumentValue)
      }
    }
  }
}
