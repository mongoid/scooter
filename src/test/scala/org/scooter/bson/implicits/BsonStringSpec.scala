import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.implicits.BsonString
import org.scooter.bson.Serialization._

import org.scooter.spec.Spec

class BsonStringSpec extends Spec {

  "BsonString#bsonWrite" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 11)
    val wrapper = new BsonString(stringValue)

    "serialize the string to the buffer" in {
      wrapper.bsonWrite(buffer, field)
      buffer.array must beEqualTo(dumpedString)
    }
  }

  "String#bsonWrite" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 11)

    "serialize the string to the buffer" in {
      stringValue.bsonWrite(buffer, field)
      buffer.array must beEqualTo(dumpedString)
    }
  }

  "BsonString.bsonRead" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 11)
    var doc = new Document

    "load the key and value into the hash" in {
      buffer.writeBytes(loadedString)
      BsonString.bsonRead(buffer, doc)
      doc must beEqualTo(Document(field -> stringValue))
    }
  }
}
