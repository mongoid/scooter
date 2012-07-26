import org.mongoid.scooter.bson.Document
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer
import org.specs2.mutable.Specification
import scala.collection.immutable.HashMap

class DocumentSpec extends Specification {

  val bytes = Array[Byte](16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)

  "Document#bsonDump" should {

    val buffer = MutableBuffer(16)
    val hash = HashMap("hi" -> "ya")
    val document = new Document(hash)

    "serializes the string to the buffer" in {
      document.bsonDump(buffer) {
        case (key: String, value: String) => value.bsonDump(buffer, key)
      }
      buffer.array must beEqualTo(bytes)
    }
  }
}
