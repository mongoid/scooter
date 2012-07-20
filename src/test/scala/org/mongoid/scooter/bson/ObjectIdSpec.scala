import java.net.InetAddress
import java.security.MessageDigest

import org.mongoid.scooter.bson.ObjectId

import org.scalatest.FunSpec
import org.scalatest.matchers.MustMatchers

class ObjectIdSpec extends FunSpec with MustMatchers {

  describe("ObjectId") {

    describe(".apply") {

      describe("when no arguments are provided") {

        it("starts the counter at zero") {
          ObjectId().data.last must be(0)
        }

        it("increments the counter each instantiation") {
          ObjectId().data.last must be(1)
        }
      }
    }
  }
}
