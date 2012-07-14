import java.nio.{ ByteBuffer, ByteOrder }

import org.mongoid.scooter.bson.StringWrapper
import org.mongoid.scooter.bson.Conversions._

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class StringWrapperSpec extends FunSpec with MustMatchers {

  describe("org.mongoid.scooter.bson.StringWrapper") {

    val buffer = ByteBuffer.allocate(18).order(ByteOrder.LITTLE_ENDIAN)

    describe("#bsonDump") {

      val key = "hello"
      val value = "world"

      val wrapper = new StringWrapper("world")

      it("serializes the string to the buffer") {
        wrapper.bsonDump(buffer, key)
      }
    }
  }

  describe("java.lang.String") {

    val buffer = ByteBuffer.allocate(18).order(ByteOrder.LITTLE_ENDIAN)

    describe("#bsonDump") {

      val key = "hello"
      val value = "world"

      it("serializes the string to the buffer") {
        value.bsonDump(buffer, key)
      }
    }
  }
}
