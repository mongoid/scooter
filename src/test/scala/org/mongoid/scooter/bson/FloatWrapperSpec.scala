import org.mongoid.scooter.bson.FloatWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class FloatWrapperSpec extends Specification {

  "FloatWrapper#bsonDump" should {

    val buffer = MutableBuffer(12)
    val key = "hi"
    val value = 1.123f
    val wrapper = new FloatWrapper(value)

    "serialize the float to the buffer" in new scope {
      wrapper.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  "Float#bsonDump" should {

    val buffer = MutableBuffer(12)
    val key = "hi"
    val value = 1.123f

    "serialize the float to the buffer" in new scope {
      value.bsonDump(buffer, key)
      buffer.array must beEqualTo(bytes)
    }
  }

  trait scope extends Scope {

    val bytes = Array[Byte](1, 104, 105, 0, 0, 0, 0, -32, -50, -9, -15, 63)
  }
}
