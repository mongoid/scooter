import org.scooter.bson._
import org.scooter.bson.Serialization._
import org.scooter.bson.implicits._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class SerializationSpec extends Specification {

  "Serialization.dumpable" should {

    "when provided a double" in {

      val rich = dumpable(1.22d)

      "returns a wrapped double" in {
        rich must beAnInstanceOf[BsonDouble]
      }
    }

    "when provided a boolean" in {

      val rich = dumpable(true)

      "returns a wrapped boolean" in {
        rich must beAnInstanceOf[BsonBoolean]
      }
    }

    "when provided an int" in {

      val rich = dumpable(1)

      "returns a wrapped int" in {
        rich must beAnInstanceOf[BsonInt]
      }
    }

    "when provided a long" in {

      val rich = dumpable(2l)

      "returns a wrapped long" in {
        rich must beAnInstanceOf[BsonLong]
      }
    }

    "when provided a string" in {

      val rich = dumpable("test")

      "returns a wrapped string" in {
        rich must beAnInstanceOf[BsonString]
      }
    }
  }
}
