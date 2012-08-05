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

  "Bytes.Null" should {

    "return 0x00" in {
      Bytes.Null must beEqualTo(0x00)
    }
  }

  "Bytes.Float" should {

    "return 0x01" in {
      Bytes.Float must beEqualTo(0x01)
    }
  }

  "Bytes.String" should {

    "return 0x02" in {
      Bytes.String must beEqualTo(0x02)
    }
  }

  "Bytes.Embedded" should {

    "return 0x03" in {
      Bytes.Embedded must beEqualTo(0x03)
    }
  }

  "Bytes.Array" should {

    "return 0x04" in {
      Bytes.Array must beEqualTo(0x04)
    }
  }

  "Bytes.Binary" should {

    "return 0x05" in {
      Bytes.Binary must beEqualTo(0x05)
    }
  }

  "Bytes.ObjectId" should {

    "return 0x07" in {
      Bytes.ObjectId must beEqualTo(0x07)
    }
  }

  "Bytes.Boolean" should {

    "return 0x08" in {
      Bytes.Boolean must beEqualTo(0x08)
    }
  }

  "Bytes.Time" should {

    "return 0x09" in {
      Bytes.Time must beEqualTo(0x09)
    }
  }

  "Bytes.NullValue" should {

    "return 0x0A" in {
      Bytes.NullValue must beEqualTo(0x0A)
    }
  }

  "Bytes.Regex" should {

    "return 0x0B" in {
      Bytes.Regex must beEqualTo(0x0B)
    }
  }

  "Bytes.Code" should {

    "return 0x0D" in {
      Bytes.Code must beEqualTo(0x0D)
    }
  }

  "Bytes.Symbol" should {

    "return 0x0E" in {
      Bytes.Symbol must beEqualTo(0x0E)
    }
  }

  "Bytes.CodeWithScope" should {

    "return 0x0F" in {
      Bytes.CodeWithScope must beEqualTo(0x0F)
    }
  }

  "Bytes.Int32" should {

    "return 0x10" in {
      Bytes.Int32 must beEqualTo(0x10)
    }
  }

  "Bytes.Int64" should {

    "return 0x12" in {
      Bytes.Int64 must beEqualTo(0x12)
    }
  }
}
