package org.scooter.bson

import java.lang.management.ManagementFactory
import java.net.InetAddress
import java.nio.ByteBuffer
import java.security.MessageDigest
import java.util.concurrent.atomic.AtomicInteger

/**
 * Companion object for a BSON ObjectId. All instantiations of the
 * object should happen through this singleton class.
 */
object ObjectId {

  /**
   * Instantiate a new object id given no arguments.
   *
   * @return The ObjectId with the generated byte sequence.
   */
  def apply() = new ObjectId(Generator.next)

  /**
   * Encapsulates behaviour around the generation of object id byte data.
   */
  private object Generator {

    /**
     * Generate the next sequence of bytes based off the atomic counter.
     *
     * @return The array of Bytes for the id.
     */
    def next = generate(COUNTER.getAndIncrement)

    /**
     * Get the counter used in id incrementing.
     *
     * @return The AtomicInteger counter.
     */
    private final val COUNTER = new AtomicInteger

    /**
     * Get the machine id bytes.
     *
     * @return The Bytes for the machine id.
     */
    private final val MACHINE = machine

    /**
     * Get the process id.
     *
     * @return The Bytes for the process id.
     */
    private final val PID = pid

    /**
     * Generate the bytes for the object id and increment with the provided
     * counter.
     *
     * @note The order in which bytes must be placed in the buffer:
     *  - 4 bytes for the current time.
     *  - 2 bytes for the MD5 hash of the machine host name.
     *  - 2 bytes for the pid of the current process.
     *  - 4 bytes for a counter.
     *
     * @param counter The next count in the sequence.
     *
     * @return The bytes for the object id.
     */
    private def generate(counter: Int) = {
      ByteBuffer.
        allocate(12).
        putInt(System.currentTimeMillis.asInstanceOf[Int]).
        put(machine).
        put(pid).
        putInt(counter).
        array
    }

    /**
     * Get the machine id as a sequence of Bytes.
     *
     * @return The Bytes for the machine id.
     */
    private def machine = {
      val md5 = MessageDigest.getInstance("MD5")
      md5.reset
      md5.update(InetAddress.getLocalHost.getHostName.getBytes)
      md5.digest.slice(0, 2)
    }

    /**
     * Get the process id.
     *
     * @return The Bytes for the process id.
     */
    private def pid = {
      ManagementFactory.getRuntimeMXBean.getName.getBytes.slice(0, 2)
    }
  }
}

/**
 * Represents a 12 byte object id in the database.
 *
 * @link http://www.mongodb.org/display/DOCS/Object+IDs
 *
 * @param bytes The raw Bytes for the id.
 */
class ObjectId(bytes: Array[Byte]) {

  /**
   * Get the raw Bytes for this object id.
   *
   * @return The raw Bytes.
   */
  def data = bytes

  /**
   * An ObjectId is equal to another if the data is the same.
   *
   * @param other The object to compare to.
   *
   * @return If the objects are equal.
   */
  override def equals(other: Any) : Boolean = {
    other.isInstanceOf[ObjectId] && (this.hashCode == other.hashCode)
  }

  /**
   * Generate a hash code for the ObjectId from the data.
   *
   * @return The hash code from the data.
   */
  override def hashCode = data.hashCode
}