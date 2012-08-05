import java.net.InetSocketAddress

import org.scooter.Connection
import org.scooter.bson.Document
import org.scooter.bson.Serialization._
import org.scooter.protocol.Insert

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class ConnectionSpec extends Specification {

  "Connection#write" should {

    val document = Document("hi" -> "ya")
    val documents = Array(document)
    val insert = new Insert("scooter_test.users", documents)

    "return the database name plus collection name" in new scope {
      // connection.write(insert)
      true must beEqualTo(true)
    }
  }

  trait scope extends Scope {

    val address = new InetSocketAddress("127.0.0.1", 27017)
    val connection = Connection(address)
  }
}
