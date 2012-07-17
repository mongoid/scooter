import org.mongoid.scooter.bson.MutableBuffer
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

      val header   = Array(55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
      val flags    = Array(0, 0, 0, 0);
      val name     = Array(115, 99, 111, 111, 116, 101, 114, 95, 116, 101, 115, 116, 46, 117, 115, 101, 114, 115, 0)
      val document = Array(16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)

      val buffer = MutableBuffer(55)

      it("adds the document to the buffer") {
        val bytes = header ++ flags ++ name ++ document
        insert.serialize(buffer)
        buffer.array must be(bytes)
      }
    }

    describe("#serializeHeader") {

      val bytes = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
      val buffer = MutableBuffer(16)

      it("adds the header as 32 bit integers") {
        insert.serializeHeader(buffer)
        buffer.array must be(bytes)
      }
    }
  }
}
