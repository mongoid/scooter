package org.scooter

import java.net.InetSocketAddress

import org.scooter.spec.Spec

import org.specs2.specification.Scope

class SessionSpec extends Spec {

  "Session#selectDynamic" should {

    "when the database has been set" in {

      "return the collection for the dynamic name" in new scope {
        session.use("scooter_test")
        session.users.name must beEqualTo(collection.name)
      }
    }
  }

  "Session#use" should {

    "set the current database" in new scope {
      session.use("scooter_test")
      session.currentDatabase.name must beEqualTo(database.name)
    }
  }

  trait scope extends Scope {

    val database = Database(session, "scooter_test")
    val collection = Collection(database, "users")
  }
}
