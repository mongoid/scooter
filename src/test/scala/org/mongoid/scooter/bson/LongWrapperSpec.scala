import org.mongoid.scooter.bson.LongWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class LongWrapperSpec extends FunSpec with MustMatchers {

  val bytes = Array[Byte](18, 104, 105, 0, 1, 0, 0, 0, 0, 0, 0, 0)

  describe("LongWrapper") {

    val buffer = MutableBuffer(12)

    describe("#bsonDump") {

      val key = "hi"
      val value = 1l

      val wrapper = new LongWrapper(value)

      it("serializes the int to the buffer") {
        wrapper.bsonDump(buffer, key)
        buffer.array must be(bytes)
      }
    }
  }

  describe("Long") {

    val buffer = MutableBuffer(12)

    describe("#bsonDump") {

      val key = "hi"
      val value = 1l

      it("serializes the int to the buffer") {
        value.bsonDump(buffer, key)
        buffer.array must be(bytes)
      }
    }
  }
}
