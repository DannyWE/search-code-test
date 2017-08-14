package com.codetest.service

import com.codetest.error.ConsumeSearchCommandError
import com.codetest.model._
import com.codetest.service.organization.{OrganizationParser, OrganizationTermMapping}
import com.codetest.util.{ErrorOr, GetResourceContent}

object SearchCommandHandler extends PartialFunction[Command, ErrorOr[String]] {
  private val organizationJsonPath = "/organizations.json"

  override def isDefinedAt(command: Command) = command.isInstanceOf[SearchCommand]

  override def apply(command: Command) = {
    command match {
      case SearchOrganizations(term, value) =>
        (for {
          jsonStr <- GetResourceContent(organizationJsonPath)
          organizations <- OrganizationParser(jsonStr)
          filterOrganizations <- OrganizationTermMapping.map(term, value, organizations)
        } yield filterOrganizations).map(Display.forOrganization)
      case t => Left(ConsumeSearchCommandError(t))
    }
  }
}
