import org.scooter.bson.Document
import org.scooter.bson.Conversions._
import org.scooter.bson.MutableBuffer
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import scala.collection.immutable.HashMap

class DocumentSpec extends Specification {

  "Document#bsonDump" should {

    val buffer = MutableBuffer(16)
    val hash = HashMap("hi" -> "ya")
    val document = new Document(hash)

    "serializes the string to the buffer" in new scope {
      document.bsonDump(buffer) {
        case (key: String, value: String) => value.bsonDump(buffer, key)
      }
      buffer.array must beEqualTo(bytes)
    }
  }

  trait scope extends Scope {

    val bytes = Array[Byte](16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)
  }
}
