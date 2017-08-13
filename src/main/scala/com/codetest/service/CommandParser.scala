package com.codetest.service

import com.codetest.error.CommandNotFoundError
import com.codetest.model._
import com.codetest.util.{ErrorOr, GetFields}

import scala.util.matching.Regex

object CommandParser {
  def parse(commandStr: String): ErrorOr[Command] = {
    val ticketRegex = regexBuilder(1, GetFields[Ticket])
    val userRegex = regexBuilder(2, GetFields[User])
    val organizationRegex = regexBuilder(3, GetFields[Organization])

    commandStr match {
      case "quit" => Right(Quit)
      case "introduction" | "intro" => Right(Introduction)
      case "4" | "view" => Right(ListSearchableFields)
      case userRegex(term, value) => Right(SearchUsers(term, value))
      case ticketRegex(term, value) => Right(SearchTickets(term, value))
      case organizationRegex(term, value) => Right(SearchOrganizations(term, value))
      case t => Left(CommandNotFoundError(t))
    }
  }

  private def regexBuilder(number: Int, fields: List[String]): Regex = {
    s"""$number (${fields.mkString("|")})=(.*)""".r
  }
}
