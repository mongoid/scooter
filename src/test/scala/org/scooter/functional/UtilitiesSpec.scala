package org.scooter.functional

import java.net.InetSocketAddress

import org.scooter.functional.Utilities._

import org.scooter.spec.Spec

import org.specs2.specification.Scope

class UtilitiesSpec extends Spec {

  "Utilities.socketAddress" should {

    val address = new InetSocketAddress("127.0.0.1", 27017)

    "return an address for the string" in {
      socketAddress("127.0.0.1:27017") must beEqualTo(address)
    }
  }

  "Utilities.socketAddresses" should {

    val first = new InetSocketAddress("127.0.0.1", 27017)
    val second = new InetSocketAddress("127.0.0.1", 27018)
    val list = List(first, second)

    "return an address for the string" in {
      socketAddresses(List("127.0.0.1:27018", "127.0.0.1:27017")) must beEqualTo(list)
    }
  }
}
