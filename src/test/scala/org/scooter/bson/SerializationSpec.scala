package org.scooter.bson

import org.scooter.bson._
import org.scooter.bson.Serialization._
import org.scooter.bson.implicits._

import org.scooter.spec.Spec

class SerializationSpec extends Spec {

  "Serialization.bsonWritable" should {

    "when provided a double" in {

      val rich = bsonWritable(1.22d)

      "returns a wrapped double" in {
        rich must beAnInstanceOf[BsonDouble]
      }
    }

    "when provided a boolean" in {

      val rich = bsonWritable(true)

      "returns a wrapped boolean" in {
        rich must beAnInstanceOf[BsonBoolean]
      }
    }

    "when provided an int" in {

      val rich = bsonWritable(1)

      "returns a wrapped int" in {
        rich must beAnInstanceOf[BsonInt]
      }
    }

    "when provided a long" in {

      val rich = bsonWritable(2l)

      "returns a wrapped long" in {
        rich must beAnInstanceOf[BsonLong]
      }
    }

    "when provided a string" in {

      val rich = bsonWritable("test")

      "returns a wrapped string" in {
        rich must beAnInstanceOf[BsonString]
      }
    }
  }
}
