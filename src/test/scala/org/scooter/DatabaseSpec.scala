package org.scooter

import java.net.InetSocketAddress

import org.scooter.spec.Spec

import org.specs2.specification.Scope

class DatabaseSpec extends Spec {

  "Database#collection" should {

    "when provided a name" in {

      "returns the collection for the name" in new scope {
        database.collection("users").fullName must beEqualTo(collection.fullName)
      }
    }
  }

  "Database#fullName" should {

    "return the name of the database" in new scope {
      database.fullName must beEqualTo("scooter_test")
    }
  }

  trait scope extends Scope {

    val session = new Session(List(new InetSocketAddress("localhost", 27017)))
    val database = new Database(session, "scooter_test")
    val collection = new Collection(database, "users", session)
  }
}
