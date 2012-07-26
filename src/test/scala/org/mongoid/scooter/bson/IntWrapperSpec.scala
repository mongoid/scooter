import org.mongoid.scooter.bson.IntWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer
import org.specs2.mutable.Specification

class IntWrapperSpec extends Specification {

  val bytes = Array[Byte](16, 104, 105, 0, 1, 0, 0, 0)

  "IntWrapper#bsonDump" should {

    val buffer = MutableBuffer(8)
    val key = "hi"
    val value = 1
    val wrapper = new IntWrapper(value)

    "serialize the int to the buffer" in {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Int#bsonDump" should {

    val buffer = MutableBuffer(8)
    val key = "hi"
    val value = 1

    "serialize the int to the buffer" in {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }
}
