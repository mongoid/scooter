package org.scooter.bson

import java.lang.management.{ ManagementFactory => MX }
import java.net.InetAddress
import java.util.concurrent.atomic.AtomicInteger

import org.jboss.netty.buffer.{ ChannelBuffer => Buffer }

import org.scooter.bson.implicits.BsonChannelBuffer._
import org.scooter.bson.Serialization._

/**
 * Companion object for BSON ObjectIds.
 */
object ObjectId extends Loadable {

  /**
   * Gets the atomic counter for incrementing ids.
   *
   * @return The AtomicInteger.
   */
  final lazy val Inc = new AtomicInteger(0)

  /**
   * Get an integer value uniquely identifying this machine.
   *
   * @return The machine Int.
   */
  final lazy val Machine = {
    InetAddress.getLocalHost.getHostName.hashCode << 16
  }

  /**
   * Get the process id for this process.
   *
   * @return The pid Int.
   */
  final lazy val Pid = {
    MX.getRuntimeMXBean().getName().hashCode() & 0xFFFF
  }

  /**
   * Load the ObjectId value and its key from the buffer.
   *
   * @param buffer The ChannelBuffer.
   * @param doc The document to place in.
   */
  def bsonLoad(buffer: Buffer, doc: Document) = {
    doc(buffer.readCString) = buffer.readObjectId
  }

  /**
  * Generate the new ObjectId.
  *
  * @return The ObjectId.
  */
  def generate = ObjectId(time, Machine, Inc.getAndIncrement)

  /**
   * Get the current time as an Int.
   *
   * @return The Int time.
   */
  private def time = (System.currentTimeMillis/1000).toInt
}

/**
 * Represents an ObjectId, which is the primary key type for
 * MongoDB documents.
 *
 * @link http://www.mongodb.org/display/DOCS/Object+IDs
 *
 * @param time The Int time.
 * @param machine The Int for the machine id.
 * @param pid The Int for the process id.
 * @param counter The Int counter.
 */
case class ObjectId(time: Int, machine: Int, counter: Int) extends Dumpable {

  /**
   * Dump the ObjectId to the buffer in it's proper BSON format.
   *
   * @link http://bsonspec.org/#/specification
   *
    * @note The order in which bytes must be placed in the buffer:
    * - 4 bytes for the current time.
    * - 2 bytes for the MD5 hash of the machine host name.
    * - 2 bytes for the pid of the current process.
    * - 4 bytes for a counter.
   *
   * @param buffer The buffer being written to.
   * @param key The String key to this instance value.
   */
  def bsonDump(buffer: Buffer, key: String) = {}
}
