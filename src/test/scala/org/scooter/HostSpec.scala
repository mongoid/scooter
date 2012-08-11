import java.net.InetSocketAddress

import org.scooter.Host._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class HostSpec extends Specification {

  "Host.socketAddress" should {

    val address = new InetSocketAddress("127.0.0.1", 27017)

    "return an address for the string" in {
      socketAddress("127.0.0.1:27017") must beEqualTo(address)
    }
  }

  "Host.socketAddresses" should {

    val first = new InetSocketAddress("127.0.0.1", 27017)
    val second = new InetSocketAddress("127.0.0.1", 27018)
    val list = List(first, second)

    "return an address for the string" in {
      socketAddresses(List("127.0.0.1:27018", "127.0.0.1:27017")) must beEqualTo(list)
    }
  }
}
