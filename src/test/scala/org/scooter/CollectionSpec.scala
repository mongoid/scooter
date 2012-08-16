package org.scooter

import java.net.InetSocketAddress

import org.specs2.specification.Scope

import org.scooter.spec.Spec

class CollectionSpec extends Spec {

  "Collection#fullName" should {

    "return the database name plus collection name" in new scope {
      collection.fullName must beEqualTo("scooter_test.users")
    }
  }

  trait scope extends Scope {

    val session = new Session(List(new InetSocketAddress("localhost", 27017)))
    val database = new Database(session, "scooter_test")
    val collection = new Collection(database, "users", session)
  }
}
