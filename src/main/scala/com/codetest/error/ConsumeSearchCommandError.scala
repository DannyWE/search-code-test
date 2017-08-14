package com.codetest.error

import com.codetest.model.Command

final case class ConsumeSearchCommandError(command: Command) extends Error {
  override def getMessage: String = {
    s"""
       | Consume Search Command Error : $command
    """.stripMargin
  }
}
