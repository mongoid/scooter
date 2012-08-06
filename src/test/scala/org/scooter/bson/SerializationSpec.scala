import org.scooter.bson._
import org.scooter.bson.Serialization._
import org.scooter.bson.implicits._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class SerializationSpec extends Specification {

  "Serialization.bsonSerializable" should {

    "when provided a double" in {

      val serializable = bsonSerializable(1.22d)

      "returns a wrapped double" in {
        serializable must beAnInstanceOf[BsonDouble]
      }
    }

    "when provided a boolean" in {

      val serializable = bsonSerializable(true)

      "returns a wrapped boolean" in {
        serializable must beAnInstanceOf[BsonBoolean]
      }
    }

    "when provided an int" in {

      val serializable = bsonSerializable(1)

      "returns a wrapped int" in {
        serializable must beAnInstanceOf[BsonInt]
      }
    }

    "when provided a long" in {

      val serializable = bsonSerializable(2l)

      "returns a wrapped long" in {
        serializable must beAnInstanceOf[BsonLong]
      }
    }

    "when provided a string" in {

      val serializable = bsonSerializable("test")

      "returns a wrapped string" in {
        serializable must beAnInstanceOf[BsonString]
      }
    }
  }
}
