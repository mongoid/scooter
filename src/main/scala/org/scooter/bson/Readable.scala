package org.scooter.bson

import io.netty.buffer.ByteBuf

trait Readable {

  /**
   * Read the BSON from the buffer and put it's object representation
   * into the Document.
   *
   * @param buffer The buffer being read from.
   * @param doc The document to write to.
   */
  def bsonRead(buffer: ByteBuf, doc: Document) : Unit
}
