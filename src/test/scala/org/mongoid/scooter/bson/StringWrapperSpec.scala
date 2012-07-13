import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

import org.mongoid.scooter.bson.StringWrapper

import java.nio.{ ByteBuffer, ByteOrder }

class StringWrapperSpec extends FunSpec with MustMatchers {

  describe("org.mongoid.scooter.bson.StringWrapper") {

    val buffer = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN)

    describe("#bsonDump") {

      val key = "hello"
      val value = "world"

      val wrapper = new StringWrapper("world")

      it("serializes the string to the buffer") {
        wrapper.bsonDump(buffer, key)
        buffer.array.foreach(arg => println(arg))
      }
    }
  }

  describe("java.lang.String") {

    describe("#bsonDump") {

    }
  }
}
