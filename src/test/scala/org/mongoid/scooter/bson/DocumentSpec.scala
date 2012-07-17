import org.mongoid.scooter.bson.Document
import org.mongoid.scooter.bson.Conversions._
import org.mongoid.scooter.bson.MutableBuffer

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

import scala.collection.immutable.HashMap

class DocumentSpec extends FunSpec with MustMatchers {

  val bytes = Array[Byte](16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)

  describe("Document") {

    val buffer = MutableBuffer(16)

    describe("#bsonDump") {

      val hash = HashMap("hi" -> "ya")
      val document = new Document(hash)

      it("serializes the string to the buffer") {
        document.bsonDump(buffer) {
          case (key: String, value: String) => value.bsonDump(buffer, key)
        }
        buffer.array must be(bytes)
      }
    }
  }
}
