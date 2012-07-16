import org.mongoid.scooter.bson.Bytes._

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class Bytes extends FunSpec with MustMatchers {

  describe("org.mongoid.scooter.bson.Bytes") {

    describe(".NULL") {

      it("returns 0x00") { NULL must be(0x00) }
    }

    describe(".FLOAT") {

      it("returns 0x01") { FLOAT must be(0x01) }
    }

    describe(".STRING") {

      it("returns 0x02") { STRING must be(0x02) }
    }

    describe(".EMBEDDED") {

      it("returns 0x03") { EMBEDDED must be(0x03) }
    }

    describe(".ARRAY") {

      it("returns 0x04") { ARRAY must be(0x04) }
    }

    describe(".BINARY") {

      it("returns 0x05") { BINARY must be(0x05) }
    }

    describe(".OBJECT_ID") {

      it("returns 0x07") { OBJECT_ID must be(0x07) }
    }

    describe(".BOOLEAN") {

      it("returns 0x08") { BOOLEAN must be(0x08) }
    }

    describe(".TIME") {

      it("returns 0x09") { TIME must be(0x09) }
    }
  }
}
