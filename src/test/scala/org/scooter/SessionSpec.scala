package org.scooter

import java.net.InetSocketAddress

import org.scooter.spec.Spec

import org.specs2.specification.Scope

class SessionSpec extends Spec {

  "Session#selectDynamic" should {

    "when the database has been set" in {

      "return the collection for the dynamic name" in new scope {
        session.use("scooter_test")
        session.users.fullName must beEqualTo(collection.fullName)
      }
    }
  }

  "Session#use" should {

    "set the current database" in new scope {
      session.use("scooter_test")
      session.currentDatabase.fullName must beEqualTo(database.fullName)
    }
  }

  trait scope extends Scope {

    val database = new Database(session, "scooter_test")
    val collection = new Collection(database, "users")
  }
}
