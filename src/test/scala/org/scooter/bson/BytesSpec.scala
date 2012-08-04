import org.scooter.bson._
import org.scooter.bson.Bytes._
import org.specs2.mutable.Specification

class BytesSpec extends Specification {

  "Bytes.getWrapper" should {

    "when passed a float byte" in {

      "return a float wrapper" in {
        Bytes.getWrapper(0x01) must beEqualTo(classOf[FloatWrapper])
      }
    }

    "when passed a string byte" in {

      "return a string wrapper" in {
        Bytes.getWrapper(0x02) must beEqualTo(classOf[StringWrapper])
      }
    }

    "when passed a boolean byte" in {

      "return a boolean wrapper" in {
        Bytes.getWrapper(0x08) must beEqualTo(classOf[BooleanWrapper])
      }
    }

    "when passed a int byte" in {

      "return a int wrapper" in {
        Bytes.getWrapper(0x10) must beEqualTo(classOf[IntWrapper])
      }
    }

    "when passed a long byte" in {

      "return a long wrapper" in {
        Bytes.getWrapper(0x12) must beEqualTo(classOf[LongWrapper])
      }
    }
  }

  "Bytes.NULL" should {

    "return 0x00" in {
      NULL must beEqualTo(0x00)
    }
  }

  "Bytes.FLOAT" should {

    "return 0x01" in {
      FLOAT must beEqualTo(0x01)
    }
  }

  "Bytes.STRING" should {

    "return 0x02" in {
      STRING must beEqualTo(0x02)
    }
  }

  "Bytes.EMBEDDED" should {

    "return 0x03" in {
      EMBEDDED must beEqualTo(0x03)
    }
  }

  "Bytes.ARRAY" should {

    "return 0x04" in {
      ARRAY must beEqualTo(0x04)
    }
  }

  "Bytes.BINARY" should {

    "return 0x05" in {
      BINARY must beEqualTo(0x05)
    }
  }

  "Bytes.OBJECT_ID" should {

    "return 0x07" in {
      OBJECT_ID must beEqualTo(0x07)
    }
  }

  "Bytes.BOOLEAN" should {

    "return 0x08" in {
      BOOLEAN must beEqualTo(0x08)
    }
  }

  "Bytes.TIME" should {

    "return 0x09" in {
      TIME must beEqualTo(0x09)
    }
  }

  "Bytes.NULL_VALUE" should {

    "return 0x0A" in {
      NULL_VALUE must beEqualTo(0x0A)
    }
  }

  "Bytes.REGEX" should {

    "return 0x0B" in {
      REGEX must beEqualTo(0x0B)
    }
  }

  "Bytes.CODE" should {

    "return 0x0D" in {
      CODE must beEqualTo(0x0D)
    }
  }

  "Bytes.SYMBOL" should {

    "return 0x0E" in {
      SYMBOL must beEqualTo(0x0E)
    }
  }

  "Bytes.SCOPED_CODE" should {

    "return 0x0F" in {
      SCOPED_CODE must beEqualTo(0x0F)
    }
  }

  "Bytes.INT_32" should {

    "return 0x10" in {
      INT_32 must beEqualTo(0x10)
    }
  }

  "Bytes.INT_64" should {

    "return 0x12" in {
      INT_64 must beEqualTo(0x12)
    }
  }
}
