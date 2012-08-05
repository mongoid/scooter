import org.scooter.{ Collection, Database, Session }

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class DatabaseSpec extends Specification {

  "Database#collection" should {

    "when provided a name" in {

      "returns the collection for the name" in new scope {
        database.collection("users") must beEqualTo(collection)
      }
    }
  }

  "Database#fullName" should {

    "return the name of the database" in new scope {
      database.fullName must beEqualTo("scooter_test")
    }
  }

  trait scope extends Scope {

    val session = new Session(Array("localhost:27017"))
    val database = new Database(session, "scooter_test")
    val collection = new Collection(database, "users")
  }
}
