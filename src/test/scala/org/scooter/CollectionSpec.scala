package org.scooter

import org.scooter.bson.Document
import org.scooter.spec.Spec

class CollectionSpec extends Spec {

  "Collection#drop" should {

    val document = new Document

    "drop the entire collection" in {
      println(session.users.drop)
    }
  }
}
