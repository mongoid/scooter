import org.mongoid.scooter.{ Collection, Database, Session }
import org.specs2.mutable.Specification

class DatabaseSpec extends Specification {

  def session = new Session(Array("localhost:27017"))

  "Database#collection" should {

    "when provided a name" in {

      def database = new Database(session, "scooter_test")
      def collection = new Collection(database, "users")

      "returns the collection for the name" in {
        database.collection("users") must beEqualTo(collection)
      }
    }
  }

  "Database#fullName" should {

    def database = new Database(session, "scooter_test")

    "return the name of the database" in {
      database.fullName must beEqualTo("scooter_test")
    }
  }
}
