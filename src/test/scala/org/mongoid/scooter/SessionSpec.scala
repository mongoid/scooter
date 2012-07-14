import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

import org.mongoid.scooter.Collection
import org.mongoid.scooter.Database
import org.mongoid.scooter.Session

class SessionSpec extends FunSpec with MustMatchers {

  describe("org.mongoid.scooter.Session") {

    val session = new Session(Array("localhost:27017"))

    describe("#collection_name") {

      val database = new Database(session, "scooter_test")
      val collection = new Collection(database, "users")

      it("returns the collection for the method name") {
        session.use("scooter_test")
        session.users must be(collection)
      }
    }

    describe("#use") {

      val database = new Database(session, "scooter_test")

      it("sets the session's current database") {
        session.use("scooter_test")
        session.database must be(database)
      }
    }
  }
}
