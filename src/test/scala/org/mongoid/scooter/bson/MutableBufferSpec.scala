import org.mongoid.scooter.bson._

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class MutableBufferSpec extends FunSpec with MustMatchers {

  describe("MutableBuffer") {

    describe("#putByte") {

      describe("when the buffer would not overflow") {

        val buffer = MutableBuffer(1)
        val result = buffer.putByte(Bytes.STRING)

        it("puts the byte into the buffer") {
          result.array must be(Array(Bytes.STRING))
        }

        it("returns the buffer") {
          result must be(buffer)
        }
      }

      describe("when the buffer would overflow") {

        val buffer = MutableBuffer(1)
        val result = buffer.putByte(Bytes.STRING)

        it("puts the byte into the buffer") {
          buffer.putByte(Bytes.STRING)
          result.array must be(Array(Bytes.STRING, Bytes.STRING))
        }
      }
    }

    describe("#putInt") {

      describe("when the buffer would not overflow") {

        val buffer = MutableBuffer(4)
        val result = buffer.putInt(1)

        it("puts the byte into the buffer") {
          result.array must be(Array(1, 0, 0, 0))
        }

        it("returns the buffer") {
          result must be(buffer)
        }
      }

      describe("when the buffer would overflow") {

        val buffer = MutableBuffer(4)
        val result = buffer.putInt(1)

        it("puts the byte into the buffer") {
          val result = buffer.putInt(1)
          result.array must be(Array(1, 0, 0, 0, 1, 0, 0, 0))
        }
      }
    }

    describe("#putString") {

      describe("when the buffer would not overflow") {

        val buffer = MutableBuffer(1)
        val result = buffer.putString("a")

        it("puts the byte into the buffer") {
          result.array must be(Array(97))
        }

        it("returns the buffer") {
          result must be(buffer)
        }
      }

      describe("when the buffer would overflow") {

        val buffer = MutableBuffer(1)
        val result = buffer.putString("a")

        it("puts the byte into the buffer") {
          val result = buffer.putString("a")
          result.array must be(Array(97, 97))
        }
      }
    }
  }
}
