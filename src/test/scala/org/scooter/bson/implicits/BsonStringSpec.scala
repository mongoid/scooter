package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonStringSpec extends Spec {

  "BsonString#write" should {

    val buff = buffer(11).order(LITTLE_ENDIAN)
    val wrapper = new BsonString(stringValue)

    "encode the string to the buffer" in {
      wrapper.write(buff, field)
      buff.array must beEqualTo(dumpedString)
    }
  }

  "String#write" should {

    val buff = buffer(11).order(LITTLE_ENDIAN)

    "encode the string to the buffer" in {
      stringValue.write(buff, field)
      buff.array must beEqualTo(dumpedString)
    }
  }

  "BsonString.read" should {

    val buff = buffer(11).order(LITTLE_ENDIAN)
    var doc = new Document

    "load the key and value into the hash" in {
      buff.writeBytes(loadedString)
      BsonString.read(buff, doc)
      doc must beEqualTo(Document(field -> stringValue))
    }
  }
}
