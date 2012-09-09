package org.scooter

import java.net.InetSocketAddress

import org.scooter.bson.Document
import org.scooter.bson.Serialization._
import org.scooter.protocol.{ Header, Insert, Query }

import org.scooter.spec.Spec

import org.specs2.specification.Scope

class ConnectionSpec extends Spec {

  "Connection#send" should {

    "when writing a message that does not expect replies" in {

      val document = Document("hi" -> "ya")
      val documents = Array(document)
      val header = Header(0, 0, 2002)
      val insert = new Insert(header, "scooter_test.users", documents)

      "writes the message" in new scope {
        // connection.write(insert)
      }
    }

    "when writing a message that expects replies" in {

      val header = Header(0, 0, 2004)
      val query = new Query(header, "scooter_test.users", Document("hi" -> "ya"), 0, 0)

      "writes the message" in new scope {
        // connection.send(query)
      }
    }
  }

  trait scope extends Scope {

    val address = new InetSocketAddress("127.0.0.1", 27017)
    // val connection = Connection(address)
  }
}
