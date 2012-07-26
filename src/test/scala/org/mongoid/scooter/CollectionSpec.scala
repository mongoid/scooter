import org.mongoid.scooter.{ Collection, Database, Session }
import org.specs2.mutable.Specification

class CollectionSpec extends Specification {

  def session = new Session(Array("localhost:27017"))
  def database = new Database(session, "scooter_test")

  "Collection#fullName" should {

    def collection = new Collection(database, "users")

    "return the database name plus collection name" in {
      collection.fullName must beEqualTo("scooter_test.users")
    }
  }
}
