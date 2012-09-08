package org.scooter.spec

import org.scooter.functional.Utilities._
import org.scooter.Session

import org.specs2.mutable.Specification
import org.specs2.specification.AfterExample
import org.specs2.specification.Scope

class Spec extends Specification with Data with AfterExample {

  def session = {
    Session("127.0.0.1:27017").tap(session => session.use("scooter_test"))
  }

  def after = session.shutdown
}
