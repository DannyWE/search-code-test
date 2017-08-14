package com.codetest.service

import com.codetest.model.{Organization, TicketExtension, UserExtension}

object Display {
  def forModel[T](listT: List[T], toStr: T => String): String = {
    listT match {
      case Nil => "No Results Found"
      case head :: Nil =>
        s"""
           |1 Result Found
           |${toStr(head)}
         """.stripMargin
      case list =>
        s"""
           |${list.length} Results Found
           |${list.map(toStr)}
         """.stripMargin
    }
  }

  def forUser(userExtension: UserExtension): String = {
    s"""
       |_id: ${userExtension.user._id}
       |url: ${userExtension.user.url}
       |external_id: ${userExtension.user.external_id}
       |name: ${userExtension.user.name}
       |created_at: ${userExtension.user.created_at}
       |last_login_at: ${userExtension.user.last_login_at}
       |phone: ${userExtension.user.phone}
       |signature: ${userExtension.user.signature}
       |tags: ${userExtension.user.tags.mkString(", ")}
       |role: ${userExtension.user.role}
       |active: ${userExtension.user.active}
       |shared: ${userExtension.user.shared}
       |suspended: ${userExtension.user.suspended}
       |verified: ${userExtension.user.verified.getOrElse("")}
       |alias: ${userExtension.user.alias.getOrElse("")}
       |timezone: ${userExtension.user.timezone.getOrElse("")}
       |organization_id: ${userExtension.user.organization_id.getOrElse("")}
       |locale: ${userExtension.user.locale.getOrElse("")}
       |email: ${userExtension.user.email.getOrElse("")}
       |organization name: ${userExtension.organization.map(_.name).getOrElse("")}
       |submit tickets number: ${userExtension.submitTicket.length}
       |submit tickets: ${userExtension.submitTicket.map(_._id).mkString(", ")}
       |assign tickets number: ${userExtension.assignTicket.length}
       |assign tickets: ${userExtension.assignTicket.map(_._id).mkString(", ")}
        """.stripMargin
  }

  def forTicket(ticketExtension: TicketExtension): String = {
    s"""
       |_id: ${ticketExtension.ticket._id}
       |external_id: ${ticketExtension.ticket.external_id}
       |created_at: ${ticketExtension.ticket.created_at}
       |subject: ${ticketExtension.ticket.subject}
       |priority: ${ticketExtension.ticket.priority}
       |status: ${ticketExtension.ticket.status}
       |tags: ${ticketExtension.ticket.tags.mkString(", ")}
       |has_incidents: ${ticketExtension.ticket.has_incidents}
       |via: ${ticketExtension.ticket.via}
       |submitter_id: ${ticketExtension.ticket.submitter_id.getOrElse("")}
       |submitter_name: ${ticketExtension.submitter.map(_.name).getOrElse("")}
       |organization_id: ${ticketExtension.ticket.organization_id.getOrElse("")}
       |organization_name: ${ticketExtension.organization.map(_.name).getOrElse("")}
       |assignee_id: ${ticketExtension.ticket.assignee_id.getOrElse("")}
       |assignee_name: ${ticketExtension.assigner.map(_.name).getOrElse("")}
        """.stripMargin
  }

  def forOrganization(organization: Organization): String = {
    s"""
       |_id: ${organization._id}
       |url: ${organization.url}
       |external_id: ${organization.external_id}
       |name: ${organization.name}
       |created_at: ${organization.created_at}
       |details: ${organization.details}
       |shared_tickets: ${organization.shared_tickets}
       |domain_names: ${organization.domain_names.mkString(", ")}
       |tags: ${organization.tags.mkString(", ")}
        """.stripMargin
  }
}
