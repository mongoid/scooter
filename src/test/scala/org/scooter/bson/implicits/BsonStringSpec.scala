import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonString
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonStringSpec extends Spec {

  "BsonString#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 11)
    val wrapper = new BsonString(stringValue)

    "serialize the string to the buffer" in {
      wrapper.bsonDump(buffer, field)
      buffer.array must beEqualTo(dumpedString)
    }
  }

  "String#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 11)

    "serialize the string to the buffer" in {
      stringValue.bsonDump(buffer, field)
      buffer.array must beEqualTo(dumpedString)
    }
  }

  "BsonString.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 11)
    var doc = new Document

    "load the key and value into the hash" in {
      buffer.writeBytes(loadedString)
      BsonString.bsonLoad(buffer, doc)
      doc must beEqualTo(Document(field -> stringValue))
    }
  }
}
