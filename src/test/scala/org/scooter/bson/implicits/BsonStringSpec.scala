package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonStringSpec extends Spec {

  "BsonString#bsonWrite" should {

    val buff = buffer(11).order(LITTLE_ENDIAN)
    val wrapper = new BsonString(stringValue)

    "encode the string to the buffer" in {
      wrapper.bsonWrite(buff, field)
      buff.array must beEqualTo(dumpedString)
    }
  }

  "String#bsonWrite" should {

    val buff = buffer(11).order(LITTLE_ENDIAN)

    "encode the string to the buffer" in {
      stringValue.bsonWrite(buff, field)
      buff.array must beEqualTo(dumpedString)
    }
  }

  "BsonString.bsonRead" should {

    val buff = buffer(11).order(LITTLE_ENDIAN)
    var doc = new Document

    "load the key and value into the hash" in {
      buff.writeBytes(loadedString)
      BsonString.bsonRead(buff, doc)
      doc must beEqualTo(Document(field -> stringValue))
    }
  }
}
