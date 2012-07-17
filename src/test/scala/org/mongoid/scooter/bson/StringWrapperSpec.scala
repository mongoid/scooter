import org.mongoid.scooter.bson.StringWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class StringWrapperSpec extends FunSpec with MustMatchers {

  val bytes = Array[Byte](2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0)

  describe("StringWrapper") {

    val buffer = MutableBuffer(11)

    describe("#bsonDump") {

      val key = "hi"
      val value = "ya"

      val wrapper = new StringWrapper(value)

      it("serializes the string to the buffer") {
        wrapper.bsonDump(buffer, key)
        buffer.array must be(bytes)
      }
    }
  }

  describe("String") {

    val buffer = MutableBuffer(11)

    describe("#bsonDump") {

      val key = "hi"
      val value = "ya"

      it("serializes the string to the buffer") {
        value.bsonDump(buffer, key)
        buffer.array must be(bytes)
      }
    }
  }
}
