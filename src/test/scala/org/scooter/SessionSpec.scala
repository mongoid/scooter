import java.net.InetSocketAddress

import org.scooter.{ Collection, Database, Session }

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class SessionSpec extends Specification {

  "Session#selectDynamic" should {

    "when the database has been set" in {

      "return the collection for the dynamic name" in new scope {
        session.use("scooter_test")
        session.users must beEqualTo(collection)
      }
    }
  }

  "Session#use" should {

    "set the current database" in new scope {
      session.use("scooter_test")
      session.database must beEqualTo(database)
    }
  }

  trait scope extends Scope {

    val session = Session("localhost:27017")
    val database = new Database(session, "scooter_test")
    val collection = new Collection(database, "users")
  }
}
