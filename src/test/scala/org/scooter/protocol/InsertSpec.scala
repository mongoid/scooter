import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._
import org.scooter.protocol.{ Header, Insert }

import org.scooter.spec.Spec

class InsertSpec extends Spec {

  "Insert#serialize" should {

    val insert = new Insert(headerValue, "scooter_test.users", Array(documentValue))
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 55)

    "add the document to the buffer" in {
      insert.serialize(buffer)
      buffer.array must beEqualTo(dumpedInsert)
    }
  }
}
