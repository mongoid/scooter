import java.net.InetSocketAddress

import org.mongoid.scooter.protocol.Insert
import org.mongoid.scooter.Connection

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

import scala.collection.immutable.HashMap

class ConnectionSpec extends FunSpec with MustMatchers {

  describe("org.mongoid.scooter.Connection") {

    val address = new InetSocketAddress("127.0.0.1", 27017)
    val connection = Connection(address)

    describe("#write") {

      val document = HashMap("hi" -> "ya")
      val documents = Array(document)
      val insert = new Insert("scooter_test.users", documents)

      it("it writes the messages to the socket") {
        connection.write(insert)
      }
    }
  }
}
