import org.mongoid.scooter.bson.Bytes._

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class Bytes extends FunSpec with MustMatchers {

  describe("Bytes") {

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

    describe(".NULL_VALUE") {

      it("returns 0x0A") { NULL_VALUE must be(0x0A) }
    }

    describe(".REGEX") {

      it("returns 0x0B") { REGEX must be(0x0B) }
    }

    describe(".CODE") {

      it("returns 0x0D") { CODE must be(0x0D) }
    }

    describe(".SYMBOL") {

      it("returns 0x0E") { SYMBOL must be(0x0E) }
    }

    describe(".SCOPED_CODE") {

      it("returns 0x0F") { SCOPED_CODE must be(0x0F) }
    }

    describe(".INT_32") {

      it("returns 0x10") { INT_32 must be(0x10) }
    }

    describe(".INT_64") {

      it("returns 0x12") { INT_64 must be(0x12) }
    }
  }
}
