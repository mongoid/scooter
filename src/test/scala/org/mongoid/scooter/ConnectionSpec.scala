import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

import java.net.InetSocketAddress

import org.mongoid.scooter.protocol.Command
import org.mongoid.scooter.Connection

class ConnectionSpec extends FunSpec with MustMatchers {

  describe("org.mongoid.scooter.Connection") {

    val address = new InetSocketAddress("127.0.0.1", 27017)
    val connection = Connection(address)

    describe("#write") {

      val ping = new Command

      it("it writes the messages to the socket") {
        connection.write(ping)
      }
    }
  }
}
