package org.scooter

import org.scooter.bson.Document
import org.scooter.spec.Spec

class CollectionSpec extends Spec {

  "Collection#drop" should {

    "drop the entire collection" in {
      // @todo: Leaving connections open. println(session.users.drop)
    }
  }
}
