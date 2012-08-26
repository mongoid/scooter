package org.scooter

import org.scooter.bson.Document
import org.scooter.bson.Serialization._
import org.scooter.spec.Spec

import org.specs2.specification.Scope

class CriteriaSpec extends Spec {

  "Criteria#one" should {

    "when the selector matches a document" in new scope {

      val selector = Document("hi" -> "ya")
      val criteria = Criteria(collection, selector)

      "returns the first matching document" in {
      }
    }
  }

  trait scope extends Scope {

    val database = Database(session, "scooter_test")
    val collection = Collection(database, "users")
  }
}
