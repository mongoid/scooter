import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonInt
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonIntSpec extends Spec {

  "BsonInt#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)
    val wrapper = new BsonInt(intValue)

    "serialize the int to the buffer" in {
      wrapper.bsonDump(buffer, field)
      buffer.array must beEqualTo(dumpedInt)
    }
  }

  "Int#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)

    "serialize the int to the buffer" in {
      intValue.bsonDump(buffer, field)
      buffer.array must beEqualTo(dumpedInt)
    }
  }

  "BsonInt.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 8)
    val doc = new Document

    "adds the key and the int to the doc" in {
      buffer.writeBytes(loadedInt)
      BsonInt.bsonLoad(buffer, doc)
      doc must beEqualTo(Document(field -> intValue))
    }
  }
}
