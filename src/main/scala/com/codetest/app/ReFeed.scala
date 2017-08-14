package com.codetest.app

import com.codetest.model.{Organization, Ticket, User, UserExtension}
import com.codetest.service.ModelExtension
import com.codetest.service.organization.OrganizationParser
import com.codetest.service.ticket.TicketParser
import com.codetest.service.user.UserParser
import com.codetest.util.{GetResourceContent, WriteResourceContent}
import io.circe.syntax._
import io.circe.generic.auto._

object ReFeed {

  def main(args: Array[String]): Unit = {
    reFeedUsersAndTickets()
  }

  def reFeedUsersAndTickets(): Unit = {
    val usersExtensionPath = "outputs/usersExtension.json"
    val ticketsExtensionPath = "outputs/ticketsExtension.json"

    reFeedAndWriteToFile(usersExtensionPath, ModelExtension.usersExtension(_, _, _).asJson.spaces2)
    reFeedAndWriteToFile(ticketsExtensionPath, ModelExtension.ticketsExtension(_, _, _).asJson.spaces2)
  }

  private def reFeedAndWriteToFile(filePath: String, writeTo: (List[Ticket], List[User], List[Organization]) => String): Unit = {
    for {
      ticketJsonStr <- GetResourceContent("/tickets.json")
      ticketList <- TicketParser(ticketJsonStr)
      usersJsonStr <- GetResourceContent("/users.json")
      userList <- UserParser(usersJsonStr)
      organizationJsonStr <- GetResourceContent("/organizations.json")
      organizationList <- OrganizationParser(organizationJsonStr)
      _ <- WriteResourceContent(filePath, writeTo(ticketList, userList, organizationList))
    } yield ()
  }
}
