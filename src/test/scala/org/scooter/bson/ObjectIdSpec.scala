import java.net.InetAddress
import java.security.MessageDigest

import org.scooter.bson.ObjectId

import org.specs2.mutable.Specification

class ObjectIdSpec extends Specification {

  "ObjectId.apply" should {

    "when no arguments are provided" in {

      "start the counter at zero" in {
        ObjectId().data.last must beEqualTo(0)
      }
    }
  }
}
