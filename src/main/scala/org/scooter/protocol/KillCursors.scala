package org.scooter.protocol

import io.netty.buffer.ByteBuf

import org.scooter.bson.implicits.BsonByteBuf._
import org.scooter.bson.Serialization._

/**
 * Companion object for the KillCursors class.
 */
object KillCursors {

  /**
   * Instantiate a new KillCursors message.
   *
   * @param cursors The ids of the cursors to kill.
   *
   * @return The KillCursors message.
   */
  def apply(cursors: Seq[Long]) = {
    new KillCursors(Header(0, 0, 2007), cursors)
  }
}

/**
 * This class encapsulates behaviour for the OP_KILL_CURSORS command in the
 * MongoDB Wire Protocol.
 *
 * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
 *
 * @param header The Header.
 * @param cursors The ids of the cursors to kill.
 */
sealed case class KillCursors (header: Header, cursors: Seq[Long])
  extends Command(header, 2007) {

  /**
   * Serialize the KillCursors into a buffer that can be written to the socket.
   *
   * @link http://www.mongodb.org/display/DOCS/Mongo+Wire+Protocol
   *
   * @note The order in which bytes must be placed into the buffer:
   *  - The standard header.
   *  - A placeholder zero int32.
   *  - The number of cursors to kill.
   *  - The cursors.
   *  - Replace the length at position zero after everything is written.
   *
   * @param buffer The ByteBuf that will get written.
   */
  def encode(buffer: ByteBuf) = {
    withHeader(buffer) {
      buffer.writeInt(0) // Placeholder.
      buffer.writeInt(cursors.length)
      cursors.foreach(cursor => buffer.writeLong(cursor))
    }
  }
}
