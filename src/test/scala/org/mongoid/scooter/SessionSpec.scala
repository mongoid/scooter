import org.mongoid.scooter.{ Collection, Database, Session }
import org.specs2.mutable.{ Before, Specification }

class SessionSpec extends Specification {

  val session = new Session(Array("localhost:27017"))
  val database = new Database(session, "scooter_test")
  val collection = new Collection(database, "users")

  "Session#selectDynamic" should {

    "when the database has been set" in {

      "return the collection for the dynamic name" in scooter {
        session.users must beEqualTo(collection)
      }
    }
  }

  "Session#use" should {

    "set the current database" in scooter {
      session.database must beEqualTo(database)
    }
  }

  object scooter extends Before {

    def before = session.use("scooter_test")
  }
}
