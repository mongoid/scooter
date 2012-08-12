import java.nio.ByteOrder

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers._

import org.scooter.bson.Document
import org.scooter.bson.Serialization._
import org.scooter.protocol.Reply

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class ReplySpec extends Specification {

  "Reply.deserialize" should {

    "when the buffer has more than one document" in {

      "returns a reply with the first document" in new scope {
        writeDocuments(buffer)
        val reply = Reply.deserialize(buffer)
        reply.documents(0) must beEqualTo(Document("hi" -> "ya"))
      }

      "returns a reply with the second document" in new scope {
        writeDocuments(buffer)
        val reply = Reply.deserialize(buffer)
        reply.documents(1) must beEqualTo(Document("hj" -> "ya"))
      }
    }
  }

  trait scope extends Scope {

    val buffer = dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 52)
    val header = Array[Byte](52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
    val misc   = Array[Byte](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    val counts = Array[Byte](0, 0, 0, 0, 2, 0, 0, 0)
    val first  = Array[Byte](16, 0, 0, 0, 2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0, 0)
    val second = Array[Byte](16, 0, 0, 0, 2, 104, 106, 0, 3, 0, 0, 0, 121, 97, 0, 0)

    def writeDocuments(buffer: ChannelBuffer) {
      buffer.writeBytes(header)
      buffer.writeBytes(misc)
      buffer.writeBytes(counts)
      buffer.writeBytes(first)
      buffer.writeBytes(second)
    }
  }
}
