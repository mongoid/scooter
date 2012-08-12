import java.net.InetSocketAddress

import org.scooter.Connection
import org.scooter.bson.Document
import org.scooter.bson.Serialization._
import org.scooter.protocol.{ Insert, Query }

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class ConnectionSpec extends Specification {

  "Connection#write" should {

    "when writing a message that does not expect replies" in {

      val document = Document("hi" -> "ya")
      val documents = Array(document)
      val insert = new Insert("scooter_test.users", documents)

      "writes the message" in new scope {
        // connection.write(insert)
        true
      }
    }

    "when writing a message that expects replies" in {

      val query = new Query("scooter_test.users", Document("hi" -> "ya"))

      "writes the message" in new scope {
        connection.write(query)
        true
      }
    }
  }

  trait scope extends Scope {

    val address = new InetSocketAddress("127.0.0.1", 27017)
    val connection = Connection(address)
  }
}
