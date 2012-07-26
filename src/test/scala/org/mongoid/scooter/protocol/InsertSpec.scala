import org.mongoid.scooter.bson.MutableBuffer
import org.mongoid.scooter.protocol.Insert
import org.specs2.mutable.Specification
import scala.collection.immutable.HashMap

class InsertSpec extends Specification {

  val doc = HashMap("hi" -> "ya")
  val documents = Array(doc)
  val insert = new Insert("scooter_test.users", documents)

  "Insert#serialize" should {

    val header = Array(55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
    val flags = Array(0, 0, 0, 0);
    val name = Array(115, 99, 111, 111, 116, 101, 114, 95, 116, 101, 115, 116, 46, 117, 115, 101, 114, 115, 0)
    val document = Array(16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)
    val buffer = MutableBuffer(55)

    "add the document to the buffer" in {
      val bytes = header ++ flags ++ name ++ document
      insert.serialize(buffer)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Insert#serializeHeader" should {

    val bytes = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
    val buffer = MutableBuffer(16)

    "add the header as 32 bit integers" in {
      insert.serializeHeader(buffer)
      buffer.array must beEqualTo(bytes)
    }
  }
}
