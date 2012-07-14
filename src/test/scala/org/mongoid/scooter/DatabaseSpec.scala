import org.mongoid.scooter.{ Collection, Database, Session }

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class DatabaseSpec extends FunSpec with MustMatchers {

  describe("org.mongoid.scooter.Database") {

    val session = new Session(Array("localhost:27017"))

    describe("#collection") {

      val database = new Database(session, "scooter_test")
      val collection = new Collection(database, "users")

      it("returns the collection for the provided name") {
        database.collection("users") must be(collection)
      }
    }

    describe("#fullName") {

      val database = new Database(session, "scooter_test")

      it("returns the name of the database") {
        database.fullName must be("scooter_test")
      }
    }
  }
}
