import org.scooter.bson._
import org.scooter.bson.Serialization._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class SerializationSpec extends Specification {

  "Serialization.bsonSerializable" should {

    "when provided a double" in {

      val serializable = bsonSerializable(1.22d)

      "returns a wrapped double" in {
        serializable must beAnInstanceOf[DoubleWrapper]
      }
    }

    "when provided a boolean" in {

      val serializable = bsonSerializable(true)

      "returns a wrapped boolean" in {
        serializable must beAnInstanceOf[BooleanWrapper]
      }
    }

    "when provided an int" in {

      val serializable = bsonSerializable(1)

      "returns a wrapped int" in {
        serializable must beAnInstanceOf[IntWrapper]
      }
    }

    "when provided a float" in {

      val serializable = bsonSerializable(1.22f)

      "returns a wrapped float" in {
        serializable must beAnInstanceOf[FloatWrapper]
      }
    }

    "when provided a long" in {

      val serializable = bsonSerializable(2l)

      "returns a wrapped long" in {
        serializable must beAnInstanceOf[LongWrapper]
      }
    }

    "when provided a string" in {

      val serializable = bsonSerializable("test")

      "returns a wrapped string" in {
        serializable must beAnInstanceOf[StringWrapper]
      }
    }
  }
}
