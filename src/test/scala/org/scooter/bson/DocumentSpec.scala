import java.nio.ByteOrder
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._
import org.scooter.bson.Document
import org.scooter.bson.Conversions._
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import scala.collection.mutable.HashMap

class DocumentSpec extends Specification {

  "Document#bsonDump" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 16)
    val hash = HashMap[String, Any]("hi" -> "ya")
    val document = new Document(hash)

    "serializes the string to the buffer" in new scope {
      document.bsonDump(buffer) {
        case (key: String, value: String) => value.bsonDump(buffer, key)
      }
      buffer.array must beEqualTo(bytes)
    }
  }

  "Document.bsonLoad" should {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 16)
    val doc = new HashMap[String, Any]
    val hash = HashMap("hi" -> "ya")

    "deserialize the bytes into a hash map" in new scope {
      // buffer.put(bytes)
      // Document.bsonLoad(buffer, doc) must beEqualTo(hash)
    }
  }

  trait scope extends Scope {

    val bytes = Array[Byte](16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)
  }
}
