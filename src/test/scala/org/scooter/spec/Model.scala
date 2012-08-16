package org.scooter.spec

import org.scooter.functional.Utilities._
import org.scooter.Session

trait Model {

  def session = {
    Session("127.0.0.1:27017").tap(session => session.use("scooter_test"))
  }
}
