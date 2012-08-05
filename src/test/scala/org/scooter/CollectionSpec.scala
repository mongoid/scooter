import org.scooter.{ Collection, Database, Session }

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class CollectionSpec extends Specification {

  "Collection#fullName" should {

    "return the database name plus collection name" in new scope {
      collection.fullName must beEqualTo("scooter_test.users")
    }
  }

  trait scope extends Scope {

    val session = new Session(Array("localhost:27017"))
    val database = new Database(session, "scooter_test")
    val collection = new Collection(database, "users")
  }
}
