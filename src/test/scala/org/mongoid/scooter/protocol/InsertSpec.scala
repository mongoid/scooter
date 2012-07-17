import java.nio.{ ByteBuffer, ByteOrder }

import org.mongoid.scooter.protocol.Insert

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

import scala.collection.immutable.HashMap

class InsertSpec extends FunSpec with MustMatchers {

  describe("Insert") {

    val document = HashMap("hi" -> "ya")
    val documents = Array(document)
    val insert = new Insert("scooter_test.users", documents)

    describe("#serialize") {

      val header   = Array[Byte](55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
      val flags    = Array[Byte](0, 0, 0, 0);
      val name     = Array[Byte](
        115, 99, 111, 111, 116, 101, 114, 95, 116, 101, 115, 116, 46, 117, 115, 101, 114, 115, 0
      )
      val document = Array[Byte](16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)

      val buffer = ByteBuffer.allocate(55).order(ByteOrder.LITTLE_ENDIAN)

      it("adds the document to the buffer") {
        val bytes = header ++ flags ++ name ++ document
        insert.serialize(buffer)
        buffer.array must be(bytes)
      }
    }

    describe("#serializeHeader") {

      val bytes = Array[Byte](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
      val buffer = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN)

      it("adds the header as 32 bit integers") {
        insert.serializeHeader(buffer)
        buffer.array must be(bytes)
      }
    }
  }
}
