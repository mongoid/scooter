import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._
import org.scooter.protocol.{ Header, Insert }

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class InsertSpec extends Specification {

  "Insert#serialize" should {

    val flags = Array(0, 0, 0, 0);
    val name = Array(115, 99, 111, 111, 116, 101, 114, 95, 116, 101, 115, 116, 46, 117, 115, 101, 114, 115, 0)
    val document = Array(16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)
    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 55)

    "add the document to the buffer" in new scope {
      insert.serialize(buffer)
      val bytes = header ++ flags ++ name ++ document
      buffer.array must beEqualTo(bytes)
    }
  }

  trait scope extends Scope {

    val counter = Header.Inc.get
    val header = Array(55, 0, 0, 0, counter, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
    val doc = Document("hi" -> "ya")
    val documents = Array(doc)
    val insert = new Insert("scooter_test.users", documents)
  }
}
