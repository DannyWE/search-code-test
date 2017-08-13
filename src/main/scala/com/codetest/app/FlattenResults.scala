package com.codetest.app

import com.codetest.service.ReFeed
import com.codetest.service.organization.OrganizationParser
import com.codetest.service.ticket.TicketParser
import com.codetest.service.user.UserParser
import com.codetest.util.{GetResourceContent, WriteResourceContent}
import io.circe.syntax._
import io.circe.generic.auto._

object FlattenResults {

  def main(args: Array[String]): Unit = {
    val usersExtensionPath = "outputs/usersExtension.json"
    val ticketsExtensionPath = "outputs/ticketsExtension.json"

    val userExtensionsResult = for {
      ticketJsonStr <- GetResourceContent("/tickets.json")
      ticketArray <- TicketParser(ticketJsonStr)
      usersJsonStr <- GetResourceContent("/users.json")
      userArray <- UserParser(usersJsonStr)
      organizationJsonStr <- GetResourceContent("/organizations.json")
      organizationArray <- OrganizationParser(organizationJsonStr)
      _ <- WriteResourceContent(usersExtensionPath, ReFeed.feedUsersExtension(ticketArray, userArray, organizationArray).asJson.spaces2)
    } yield ()

    println(userExtensionsResult)

    val ticketsExtensionResult = for {
      ticketJsonStr <- GetResourceContent("/tickets.json")
      ticketArray <- TicketParser(ticketJsonStr)
      usersJsonStr <- GetResourceContent("/users.json")
      userArray <- UserParser(usersJsonStr)
      organizationJsonStr <- GetResourceContent("/organizations.json")
      organizationArray <- OrganizationParser(organizationJsonStr)
      _ <- WriteResourceContent(ticketsExtensionPath, ReFeed.feedTicketsExtension(ticketArray, userArray, organizationArray).asJson.spaces2)
    } yield ()

    println(ticketsExtensionResult)
  }
}
