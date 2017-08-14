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

  def forUser(user: UserExtension): String = {
    s"""
       |_id: ${user.user._id}
       |active: ${user.user.active}
       |email: ${user.user.email.getOrElse("")}
       |submitted ticket number: ${user.submitTicket.length}
       |assigned ticket number: ${user.assignTicket.length}
       |organization name: ${user.organization.map(_.name).getOrElse("")}
        """.stripMargin
  }

  def forTicket(ticketExtension: TicketExtension): String = {
    s"""
       |_id: ${ticketExtension.ticket._id}
       |status: ${ticketExtension.ticket.status}
       |tags: ${ticketExtension.ticket.tags.mkString(", ")}
        """.stripMargin
  }

  def forOrganization(organization: Organization): String = {
    s"""
       |_id: ${organization._id}
       |name: ${organization.name}
       |details: ${organization.details}
        """.stripMargin
  }
}
