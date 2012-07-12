import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

import org.mongoid.scooter.bson.StringWrapper

class StringWrapperSpec extends FunSpec with MustMatchers {

  describe("org.mongoid.scooter.bson.StringWrapper") {

    describe("#bsonDump") {

      val string = "scooter"

      it("serializes the string to the buffer") {

      }
    }
  }

  describe("java.lang.String") {

    describe("#bsonDump") {

    }
  }
}
