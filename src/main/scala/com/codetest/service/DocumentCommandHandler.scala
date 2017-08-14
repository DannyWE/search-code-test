package com.codetest.service

import com.codetest.error.CommandNotFoundError
import com.codetest.model._
import com.codetest.util.{ErrorOr, GetFields}

object DocumentCommandHandler extends PartialFunction[Command, ErrorOr[String]] {
  override def isDefinedAt(x: Command) = true

  override def apply(command: Command) = {
    command match {
      case ListSearchableFields =>
        Right(
          s"""
             |--------------------------------------------
             |Search Tickets with
             |${GetFields[Ticket].mkString("\r\n")}
             |--------------------------------------------
             |Search Users with
             |${GetFields[User].mkString("\r\n")}
             |--------------------------------------------
             |Search Organizations with
             |${GetFields[Organization].mkString("\r\n")}
           """.stripMargin
        )
      case Introduction =>
        Right(s"""
           |Type 'quit' to exit at any time.
           |   Or select search options:
           |   * Press 1 to search Tickets. For simplicity i.e. 1 priority=high
           |   * Press 2 to search Users. For simplicity i.e. 2 name=Sweet Cain
           |   * Press 3 to search Organization. For simplicity i.e. 3 details=MegaCorp
           |   * Press 4 to view a list of searchable fields
         """.stripMargin)

      case t => Left(CommandNotFoundError(t.getClass.getName))
    }
  }

}
