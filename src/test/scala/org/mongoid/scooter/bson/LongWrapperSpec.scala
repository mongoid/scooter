import org.mongoid.scooter.bson.LongWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class LongWrapperSpec extends Specification {

  "LongWrapper#bsonDump" should {

    val buffer = MutableBuffer(12)
    val key = "hi"
    val value = 1l
    val wrapper = new LongWrapper(value)

    "serialize the int to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Long#bsonDump" should {

    val buffer = MutableBuffer(12)
    val key = "hi"
    val value = 1l

    "serialize the int to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  trait scope extends Scope {

    val bytes = Array[Byte](18, 104, 105, 0, 1, 0, 0, 0, 0, 0, 0, 0)
  }
}
