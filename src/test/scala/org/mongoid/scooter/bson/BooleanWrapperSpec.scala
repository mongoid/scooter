import org.mongoid.scooter.bson.BooleanWrapper
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class BooleanWrapperSpec extends FunSpec with MustMatchers {

  describe("BooleanWrapper") {

    describe("#bsonDump") {

      val key = "hi"

      describe("when the boolean is true") {

        val buffer = MutableBuffer(5)
        val bytes = Array[Byte](8, 104, 105, 0, 1)
        val wrapper = new BooleanWrapper(true)

        it("serializes the int to the buffer") {
          wrapper.bsonDump(buffer, key)
          buffer.array must be(bytes)
        }
      }

      describe("when the boolean is false") {

        val buffer = MutableBuffer(5)
        val bytes = Array[Byte](8, 104, 105, 0, 0)
        val wrapper = new BooleanWrapper(false)

        it("serializes the int to the buffer") {
          wrapper.bsonDump(buffer, key)
          buffer.array must be(bytes)
        }
      }
    }
  }

  describe("Boolean") {

    describe("#bsonDump") {

      val key = "hi"

      describe("when the boolean is true") {

        val buffer = MutableBuffer(5)
        val bytes = Array[Byte](8, 104, 105, 0, 1)

        it("serializes the boolean to the buffer") {
          true.bsonDump(buffer, key)
          buffer.array must be(bytes)
        }
      }

      describe("when the boolean is false") {

        val buffer = MutableBuffer(5)
        val bytes = Array[Byte](8, 104, 105, 0, 0)

        it("serializes the boolean to the buffer") {
          false.bsonDump(buffer, key)
          buffer.array must be(bytes)
        }
      }
    }
  }
}
