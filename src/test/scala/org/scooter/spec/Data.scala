package org.scooter.spec

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
}
