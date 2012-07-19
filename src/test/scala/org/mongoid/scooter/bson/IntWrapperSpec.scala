import org.mongoid.scooter.bson.IntWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class IntWrapperSpec extends FunSpec with MustMatchers {

  val bytes = Array[Byte](16, 104, 105, 0, 1, 0, 0, 0)

  describe("IntWrapper") {

    val buffer = MutableBuffer(8)

    describe("#bsonDump") {

      val key = "hi"
      val value = 1

      val wrapper = new IntWrapper(value)

      it("serializes the int to the buffer") {
        wrapper.bsonDump(buffer, key)
        buffer.array must be(bytes)
      }
    }
  }

  describe("Int") {

    val buffer = MutableBuffer(8)

    describe("#bsonDump") {

      val key = "hi"
      val value = 1

      it("serializes the int to the buffer") {
        value.bsonDump(buffer, key)
        buffer.array must be(bytes)
      }
    }
  }
}

