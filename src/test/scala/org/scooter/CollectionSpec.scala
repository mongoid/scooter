package org.scooter

import org.scooter.bson.Document
import org.scooter.spec.Spec

class CollectionSpec extends Spec {

  "Collection#drop" should {

    "drop the entire collection" in {

      println(session.users.drop)
    }
  }
}
