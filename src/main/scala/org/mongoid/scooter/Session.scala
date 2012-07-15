package org.mongoid.scooter

import language.implicitConversions

/**
 * Companion object for the Session class.
 */
object Session {

  /**
   * Implicit conversion from a Session to a Context.
   *
   * @param session The Session to convert.
   *
   * @return The Context for the session.
   */
  implicit def delegateToContext(session: Session) = session.context

  /**
   * Implicit conversion from a Session to a Database.
   *
   * @param session The Session to convert.
   *
   * @return The Database for the session.
   */
  implicit def delegateToDatabase(session: Session) = session.database
}

/**
 * The Session is the main entry point in Scooter for dealing with MongoDB.
 * Most operations start from an instance of a Session.
 *
 * @param hosts An Array of "host:port" String pairs.
 */
class Session(hosts: Array[String]) extends Dynamic {

  /**
   * This is the wrapped Context for this Session.
   *
   * @return The Context.
   */
  val context = new Context(this)

  /**
   * The current Database the Session is operating with.
   *
   * @return The current Database.
   */
  var database: Database = null;

  /**
   * Use of Scala's experimental Dyanmic invokation in order to simulate
   * something similar to Ruby's method_missing. This way a collection
   * can be accessed from the session simply by just calling a method that
   * is the collection name.
   *
   * @param name The name of the Collection.
   *
   * @return The Collection for the provided name.
   */
  def selectDynamic(name: String) = database.collection(name)

  /**
   * Use the database as specified by the provided name.
   *
   * @param name The name of the Database.
   */
  def use(name: String) = this.database = new Database(this, name)
}
