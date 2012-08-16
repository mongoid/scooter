package org.scooter.spec

import org.scooter.bson.Document
import org.scooter.bson.Serialization._
import org.scooter.protocol.Header

trait Data {

  def field = "hi"

  def dumpedTrue = {
    Array[Byte](8, 104, 105, 0, 1)
  }

  def loadedTrue = {
    Array[Byte](104, 105, 0, 1)
  }

  def dumpedFalse = {
    Array[Byte](8, 104, 105, 0, 0)
  }

  def loadedFalse = {
    Array[Byte](104, 105, 0, 0)
  }

  val doubleValue = 1.123d

  def dumpedDouble = {
    Array[Byte](1, 104, 105, 0, 43, -121, 22, -39, -50, -9, -15, 63)
  }

  def loadedDouble = {
    Array[Byte](104, 105, 0, 43, -121, 22, -39, -50, -9, -15, 63)
  }

  val intValue = 1

  def dumpedInt = {
    Array[Byte](16, 104, 105, 0, 1, 0, 0, 0)
  }

  def loadedInt = {
    Array[Byte](104, 105, 0, 1, 0, 0, 0)
  }

  val longValue = 1l

  def dumpedLong = {
    Array[Byte](18, 104, 105, 0, 1, 0, 0, 0, 0, 0, 0, 0)
  }

  def loadedLong = {
    Array[Byte](104, 105, 0, 1, 0, 0, 0, 0, 0, 0, 0)
  }

  val stringValue = "ya"

  def dumpedString = {
    Array[Byte](2, 104, 105, 0, 3, 0, 0, 0, 121, 97, 0)
  }

  def loadedString = {
    Array[Byte](104, 105, 0, 3, 0, 0, 0, 121, 97, 0)
  }

  val dumpedAltString = {
    Array[Byte](2, 104, 106, 0, 3, 0, 0, 0, 121, 97, 0)
  }

  val documentValue = Document("hi" -> "ya")

  def dumpedDocument = {
    Array[Byte](16, 0, 0, 0) ++ dumpedString ++ Array[Byte](0)
  }

  val multiDocumentValue = Document("hi" -> "ya", "hj" -> "ya")

  def dumpedMultiDocument = {
    Array[Byte](27, 0, 0, 0) ++ dumpedString ++ dumpedAltString ++ Array[Byte](0)
  }

  val headerValue = Header(0, 0, 0, 2002)

  def dumpedHeader = {
    Array[Byte](0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
  }

  def dumpedFlags = {
    Array[Byte](0, 0, 0, 0)
  }

  val collectionName = "scooter_test.users"

  def dumpedCollectionName = {
    Array[Byte](115, 99, 111, 111, 116, 101, 114, 95, 116, 101, 115, 116) ++
      Array[Byte](46, 117, 115, 101, 114, 115, 0)
  }

  def dumpedInsertHeader = {
    Array[Byte](55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
  }

  def dumpedInsert = {
    dumpedInsertHeader ++ dumpedFlags ++ dumpedCollectionName ++ dumpedDocument
  }

  val queryHeaderValue = Header(0, 0, 0, 2004)

  def dumpedQueryHeader = {
    Array[Byte](63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -44, 7, 0, 0)
  }

  def dumpedVector = {
    Array[Byte](0, 0, 0, 0)
  }

  def dumpedOptions = {
    Array[Byte](0, 0, 0, 0, 0, 0, 0, 0)
  }

  def dumpedQuery = {
    dumpedQueryHeader ++ dumpedVector ++ dumpedCollectionName ++
      dumpedOptions ++ dumpedDocument
  }

  def dumpedReplyHeader = {
    Array[Byte](52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)
  }

  def dumpedCursorId = {
    Array[Byte](0, 0, 0, 0, 0, 0, 0, 0)
  }

  def dumpedSkip = {
    Array[Byte](0, 0, 0, 0)
  }

  def dumpedCount = {
    Array[Byte](2, 0, 0, 0)
  }

  def dumpedSecondDocument = {
    Array[Byte](16, 0, 0, 0, 2, 104, 106, 0, 3, 0, 0, 0, 121, 97, 0, 0)

  }

  def dumpedQueryDocuments = {
    dumpedDocument ++ dumpedSecondDocument
  }

  def dumpedReply = {
    dumpedReplyHeader ++ dumpedFlags ++ dumpedCursorId ++ dumpedSkip ++
      dumpedCount ++ dumpedQueryDocuments
  }

  def dumpedDeleteHeader = {
    Array[Byte](59, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
  }

  def dumpedPlaceholder = {
    Array[Byte](0, 0, 0, 0)
  }

  def dumpedDelete = {
    dumpedDeleteHeader ++ dumpedPlaceholder ++ dumpedCollectionName ++
      dumpedFlags ++ dumpedDocument
  }

  def dumpedKillHeader = {
    Array[Byte](40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -46, 7, 0, 0)
  }

  def dumpedKillPlaceholder = {
    Array[Byte](0, 0, 0, 0)
  }

  def dumpedKillCount = {
    Array[Byte](2, 0, 0, 0)
  }

  val cursorValues = Array[Long](1l, 2l)

  def dumpedKillValues = {
    Array[Byte](1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0)
  }

  def dumpedKillCursors = {
    dumpedKillHeader ++ dumpedKillPlaceholder ++
      dumpedKillCount ++ dumpedKillValues
  }
}
