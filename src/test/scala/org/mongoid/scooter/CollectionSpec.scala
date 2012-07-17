import org.mongoid.scooter.{ Collection, Database, Session }

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class CollectionSpec extends FunSpec with MustMatchers {

  describe("Collection") {

    val session = new Session(Array("localhost:27017"))
    val database = new Database(session, "scooter_test")

    describe("#fullName") {

      val collection = new Collection(database, "users")

      it("returns the database name plus collection name") {
        collection.fullName must be("scooter_test.users")
      }
    }
  }
}
