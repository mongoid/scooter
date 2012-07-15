import java.nio.{ ByteBuffer, ByteOrder }

import org.mongoid.scooter.protocol.Insert

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

import scala.collection.immutable.HashMap

class InsertSpec extends FunSpec with MustMatchers {

  describe("org.mongoid.scooter.protocol.Insert") {

    val document = HashMap("hi" -> "ya")
    val documents = Array(document)
    val insert = new Insert("scooter_test.users", documents)

    describe("#serialize") {

      val buffer = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN)

      it("adds the document to the buffer") {
        insert.serialize(buffer)
      }
    }
  }
}
