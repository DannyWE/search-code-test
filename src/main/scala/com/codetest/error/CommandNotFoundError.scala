package com.codetest.error

final case class CommandNotFoundError(invalidCommand: String) extends Error {
  override def getMessage: String = {
    s"""
       | Invalid command : $invalidCommand
    """.stripMargin
  }
}
