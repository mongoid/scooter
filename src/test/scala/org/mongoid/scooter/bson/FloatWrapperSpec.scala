import org.mongoid.scooter.bson.FloatWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class FloatWrapperSpec extends FunSpec with MustMatchers {

  val bytes = Array[Byte](1, 104, 105, 0, 0, 0, 0, -32, -50, -9, -15, 63)

  describe("FloatWrapper") {

    val buffer = MutableBuffer(12)

    describe("#bsonDump") {

      val key = "hi"
      val value = 1.123f

      val wrapper = new FloatWrapper(value)

      it("serializes the float to the buffer") {
        wrapper.bsonDump(buffer, key)
        buffer.array must be(bytes)
      }
    }
  }

  describe("Float") {

    val buffer = MutableBuffer(12)

    describe("#bsonDump") {

      val key = "hi"
      val value = 1.123f

      it("serializes the float to the buffer") {
        value.bsonDump(buffer, key)
        buffer.array must be(bytes)
      }
    }
  }
}
