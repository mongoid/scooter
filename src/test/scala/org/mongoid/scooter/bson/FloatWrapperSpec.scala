import org.mongoid.scooter.bson.FloatWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer
import org.specs2.mutable.Specification

class FloatWrapperSpec extends Specification {

  val bytes = Array[Byte](1, 104, 105, 0, 0, 0, 0, -32, -50, -9, -15, 63)

  "FloatWrapper#bsonDump" should {

    val buffer = MutableBuffer(12)
    val key = "hi"
    val value = 1.123f
    val wrapper = new FloatWrapper(value)

    "serialize the float to the buffer" in {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Float#bsonDump" should {

    val buffer = MutableBuffer(12)
    val key = "hi"
    val value = 1.123f

    "serialize the float to the buffer" in {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }
}
