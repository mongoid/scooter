import java.nio.{ ByteBuffer, ByteOrder }

import org.mongoid.scooter.bson.StringWrapper
import org.mongoid.scooter.bson.Conversions._

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class StringWrapperSpec extends FunSpec with MustMatchers {

  val bytes = Array[Byte](2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0)

  describe("org.mongoid.scooter.bson.StringWrapper") {

    val buffer = ByteBuffer.allocate(11).order(ByteOrder.LITTLE_ENDIAN)

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

  describe("java.lang.String") {

    val buffer = ByteBuffer.allocate(11).order(ByteOrder.LITTLE_ENDIAN)

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
