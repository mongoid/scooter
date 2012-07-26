import org.mongoid.scooter.bson._
import org.specs2.mutable.Specification

class MutableBufferSpec extends Specification {

  "MutableBuffer#putByte" should {

    "when the buffer would not overflow" in {

      val buffer = MutableBuffer(1)
      val result = buffer.putByte(Bytes.STRING)

      "put the byte into the buffer" in {
        result.array must beEqualTo(Array(Bytes.STRING))
      }

      "return the buffer" in {
        result must beEqualTo(buffer)
      }
    }

    "when the buffer would overflow" in {

      val buffer = MutableBuffer(1)
      val result = buffer.putByte(Bytes.STRING)

      "put the byte into the buffer" in {
        buffer.putByte(Bytes.STRING)
        result.array must beEqualTo(Array(Bytes.STRING, Bytes.STRING))
      }
    }
  }

  "MutableBuffer#putDouble" should {

    "when the buffer would not overflow" in {

      val buffer = MutableBuffer(8)
      val result = buffer.putDouble(1.1233)

      "put the double into the buffer" in {
        result.array must beEqualTo(Array(126, -116, -71, 107, 9, -7, -15, 63))
      }

      "return the buffer" in {
        result must beEqualTo(buffer)
      }
    }

    "when the buffer would overflow" in {

      val buffer = MutableBuffer(8)
      val result = buffer.putDouble(1.1233)

      "put the double into the buffer" in {
        buffer.putDouble(1.1233)
        result.array must beEqualTo(
          Array(126, -116, -71, 107, 9, -7, -15, 63, 126, -116, -71, 107, 9, -7, -15, 63)
        )
      }
    }
  }

  "MutableBuffer#putInt" should {

    "when the buffer would not overflow" in {

      val buffer = MutableBuffer(4)
      val result = buffer.putInt(1)

      "put the byte into the buffer" in {
        result.array must beEqualTo(Array(1, 0, 0, 0))
      }

      "return the buffer" in {
        result must beEqualTo(buffer)
      }
    }

    "when the buffer would overflow" in {

      val buffer = MutableBuffer(4)
      val result = buffer.putInt(1)

      "put the byte into the buffer" in {
        buffer.putInt(1)
        result.array must beEqualTo(Array(1, 0, 0, 0, 1, 0, 0, 0))
      }
    }
  }

  "MutableBuffer#putLong" should {

    "when the buffer would not overflow" in {

      val buffer = MutableBuffer(8)
      val result = buffer.putLong(1l)

      "put the byte into the buffer" in {
        result.array must beEqualTo(Array(1, 0, 0, 0, 0, 0, 0, 0))
      }

      "return the buffer" in {
        result must beEqualTo(buffer)
      }
    }

    "when the buffer would overflow" in {

      val buffer = MutableBuffer(8)
      val result = buffer.putLong(1l)

      "put the byte into the buffer" in {
        buffer.putLong(1l)
        result.array must beEqualTo(Array(1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0))
      }
    }
  }

  "MutableBuffer#putString" should {

    "when the buffer would not overflow" in {

      val buffer = MutableBuffer(1)
      val result = buffer.putString("a")

      "put the byte into the buffer" in {
        result.array must beEqualTo(Array(97))
      }

      "return the buffer" in {
        result must beEqualTo(buffer)
      }
    }

    "when the buffer would overflow" in {

      val buffer = MutableBuffer(1)
      val result = buffer.putString("a")

      "put the byte into the buffer" in {
        buffer.putString("a")
        result.array must beEqualTo(Array(97, 97))
      }
    }
  }
}
