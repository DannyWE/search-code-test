package com.codetest.service

import com.codetest.error.ConsumeSearchCommandError
import com.codetest.model._
import com.codetest.service.organization.{OrganizationParser, OrganizationTermMapping}
import com.codetest.service.ticket.{TicketExtensionParser, TicketTermMapping}
import com.codetest.service.user.{UserExtensionParser, UserTermMapping}
import com.codetest.util.{ErrorOr, GetResourceContent}

object SearchCommandHandler extends PartialFunction[Command, ErrorOr[String]] {
  private val ticketJsonPath = "/outputs/ticketsExtension.json"
  private val userJsonPath = "/outputs/usersExtension.json"
  private val organizationJsonPath = "/organizations.json"

  override def isDefinedAt(command: Command) = command.isInstanceOf[SearchCommand]

  override def apply(command: Command) = {
    command match {
      case SearchTickets(term, value) =>
        search(term, value, GetResourceContent.apply,
          ticketJsonPath, TicketExtensionParser.apply,
          TicketTermMapping.map, Display.forModel[TicketExtension],  Display.forTicket)
      case SearchUsers(term, value) =>
        search(term, value, GetResourceContent.apply,
          userJsonPath, UserExtensionParser.apply,
          UserTermMapping.map, Display.forModel[UserExtension],  Display.forUser)
      case SearchOrganizations(term, value) =>
        search(term, value, GetResourceContent.apply,
          organizationJsonPath, OrganizationParser.apply,
          OrganizationTermMapping.map, Display.forModel[Organization],  Display.forOrganization)
      case t => Left(ConsumeSearchCommandError(t))
    }
  }

  private def search[T](term: String,
                        value: String,
                        getContent: String => ErrorOr[String],
                        jsonPath: String,
                        parser: String => ErrorOr[List[T]],
                        termMapping: (String, String, List[T]) => ErrorOr[List[T]],
                        displayAll: (List[T], T => String) => String,
                        displayOne: T => String
                       ) = {
    (for {
      jsonStr <- GetResourceContent(jsonPath)
      listT <- parser(jsonStr)
      filteredListT <- termMapping(term, value, listT)
    } yield filteredListT).map(displayAll(_, displayOne))
  }
}
