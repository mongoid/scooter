import org.mongoid.scooter.bson.LongWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer
import org.specs2.mutable.Specification

class LongWrapperSpec extends Specification {

  val bytes = Array[Byte](18, 104, 105, 0, 1, 0, 0, 0, 0, 0, 0, 0)

  "LongWrapper#bsonDump" should {

    val buffer = MutableBuffer(12)
    val key = "hi"
    val value = 1l
    val wrapper = new LongWrapper(value)

    "serialize the int to the buffer" in {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Long#bsonDump" should {

    val buffer = MutableBuffer(12)
    val key = "hi"
    val value = 1l

    "serialize the int to the buffer" in {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }
}
