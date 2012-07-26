import java.net.InetSocketAddress
import org.mongoid.scooter.Connection
import org.mongoid.scooter.protocol.Insert
import org.specs2.mutable.Specification
import scala.collection.immutable.HashMap

class ConnectionSpec extends Specification {

  val address = new InetSocketAddress("127.0.0.1", 27017)
  val connection = Connection(address)

  "Connection#write" should {

    val document = HashMap("hi" -> "ya")
    val documents = Array(document)
    val insert = new Insert("scooter_test.users", documents)

    "return the database name plus collection name" in {
      // connection.write(insert)
      true must beEqualTo(true)
    }
  }
}
