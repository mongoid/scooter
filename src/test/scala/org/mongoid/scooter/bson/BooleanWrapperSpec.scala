import org.mongoid.scooter.bson.BooleanWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer
import org.specs2.mutable.{ Before, Specification }

class BooleanWrapperSpec extends Specification {

  val key = "hi"

  "BooleanWrapper#bsonDump" should {

    "when the boolean is true" in {

      val buffer = MutableBuffer(5)
      val bytes = Array[Byte](8, 104, 105, 0, 1)
      val wrapper = new BooleanWrapper(true)

      "serialize the boolean to the buffer" in {
        wrapper.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }

    "when the boolean is false" in {

      val buffer = MutableBuffer(5)
      val bytes = Array[Byte](8, 104, 105, 0, 0)
      val wrapper = new BooleanWrapper(false)

      "serialize the boolean to the buffer" in {
        wrapper.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }
  }

  "Boolean#bsonDump" should {

    "when the boolean is true" in {

      val buffer = MutableBuffer(5)
      val bytes = Array[Byte](8, 104, 105, 0, 1)

      "serialize the boolean to the buffer" in {
        true.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }

    "when the boolean is false" in {

      val buffer = MutableBuffer(5)
      val bytes = Array[Byte](8, 104, 105, 0, 0)

      "serialize the boolean to the buffer" in {
        false.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }
  }
}
