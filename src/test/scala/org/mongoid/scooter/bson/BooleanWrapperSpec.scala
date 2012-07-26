import org.mongoid.scooter.bson.BooleanWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class BooleanWrapperSpec extends Specification {

  "BooleanWrapper#bsonDump" should {

    "when the boolean is true" in {

      val buffer = MutableBuffer(5)
      val bytes = Array[Byte](8, 104, 105, 0, 1)
      val wrapper = new BooleanWrapper(true)

      "serialize the boolean to the buffer" in new scope {
        wrapper.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }

    "when the boolean is false" in {

      val buffer = MutableBuffer(5)
      val bytes = Array[Byte](8, 104, 105, 0, 0)
      val wrapper = new BooleanWrapper(false)

      "serialize the boolean to the buffer" in new scope {
        wrapper.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }
  }

  "Boolean#bsonDump" should {

    "when the boolean is true" in {

      val buffer = MutableBuffer(5)
      val bytes = Array[Byte](8, 104, 105, 0, 1)

      "serialize the boolean to the buffer" in new scope {
        true.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }

    "when the boolean is false" in {

      val buffer = MutableBuffer(5)
      val bytes = Array[Byte](8, 104, 105, 0, 0)

      "serialize the boolean to the buffer" in new scope {
        false.bsonDump(buffer, key)
        buffer.array must beEqualTo(bytes)
      }
    }
  }

  trait scope extends Scope {

    val key = "hi"
  }
}
