import org.mongoid.scooter.bson.StringWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class StringWrapperSpec extends Specification {

  "StringWrapper#bsonDump" should {

    val buffer = MutableBuffer(11)
    val key = "hi"
    val value = "ya"
    val wrapper = new StringWrapper(value)

    "serialize the string to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "String#bsonDump" should {

    val buffer = MutableBuffer(11)
    val key = "hi"
    val value = "ya"

    "serialize the string to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  trait scope extends Scope {

    val bytes = Array[Byte](2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0)
  }
}
