package org.scooter.bson.implicits

import io.netty.buffer.Unpooled._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonIntSpec extends Spec {

  "BsonInt#write" should {

    val buff = buffer(8).order(LITTLE_ENDIAN)
    val wrapper = new BsonInt(intValue)

    "encode the int to the buffer" in {
      wrapper.write(buff, field)
      buff.array must beEqualTo(dumpedInt)
    }
  }

  "Int#write" should {

    val buff = buffer(8).order(LITTLE_ENDIAN)

    "encode the int to the buffer" in {
      intValue.write(buff, field)
      buff.array must beEqualTo(dumpedInt)
    }
  }

  "BsonInt.read" should {

    val buff = buffer(8).order(LITTLE_ENDIAN)
    val doc = new Document

    "adds the key and the int to the doc" in {
      buff.writeBytes(loadedInt)
      BsonInt.read(buff, doc)
      doc must beEqualTo(Document(field -> intValue))
    }
  }
}
