package org.mongoid.scooter

/**
 * Companion object for the StringWrapper class.
 */
object Session {

  /**
   * Implicit conversion from a Session to a Context.
   *
   * @param session The Session to convert.
   * @return The Context for the session.
   */
  implicit def delegateToContext(session: Session) = session.context

  /**
   * Implicit conversion from a Session to a Database.
   *
   * @param session The Session to convert.
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
class Session(hosts: Array[String]) {

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
  var database : Database = null;

  /**
   * Use the database as specified by the provided name.
   *
   * @param name The name of the Database.
   */
  def use(name: String) = {
    this.database = new Database(this, name)
  }
}
