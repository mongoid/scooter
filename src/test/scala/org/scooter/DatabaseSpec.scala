package org.scooter

import java.net.InetSocketAddress

import org.scooter.spec.Spec

import org.specs2.specification.Scope

class DatabaseSpec extends Spec {

  "Database#collection" should {

    "when provided a name" in {

      "returns the collection for the name" in new scope {
        database.collection("users").name must beEqualTo(collection.name)
      }
    }
  }

  trait scope extends Scope {

    val database = new Database(session, "scooter_test")
    val collection = Collection(database, "users")
  }
}
