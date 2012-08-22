package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonIntSpec extends Spec {

  "BsonInt#bsonWrite" should {

    val buff = buffer(8).order(LITTLE_ENDIAN)
    val wrapper = new BsonInt(intValue)

    "serialize the int to the buffer" in {
      wrapper.bsonWrite(buff, field)
      buff.array must beEqualTo(dumpedInt)
    }
  }

  "Int#bsonWrite" should {

    val buff = buffer(8).order(LITTLE_ENDIAN)

    "serialize the int to the buffer" in {
      intValue.bsonWrite(buff, field)
      buff.array must beEqualTo(dumpedInt)
    }
  }

  "BsonInt.bsonRead" should {

    val buff = buffer(8).order(LITTLE_ENDIAN)
    val doc = new Document

    "adds the key and the int to the doc" in {
      buff.writeBytes(loadedInt)
      BsonInt.bsonRead(buff, doc)
      doc must beEqualTo(Document(field -> intValue))
    }
  }
}
