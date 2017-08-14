package com.codetest.service

import com.codetest.model._

object ModelExtension {
  def usersExtension(tickets: List[Ticket],
                     users: List[User],
                     organizations: List[Organization]): List[UserExtension] =
    users.map(user =>
      UserExtension(
        user,
        organizations.find(t => user.organization_id.exists(_.equals(t._id))),
        tickets.filter(t => t.submitter_id.exists(_.equals(user._id))),
        tickets.filter(t => t.assignee_id.exists(_.equals(user._id)))
      )
    )

  def ticketsExtension(tickets: List[Ticket],
                       users: List[User],
                       organizations: List[Organization]): List[TicketExtension] =
    tickets.map(ticket => TicketExtension(
      ticket = ticket,
      submitter = users.find(user => ticket.submitter_id.exists(_.equals(user._id))),
      assigner = users.find(user => ticket.assignee_id.exists(_.equals(user._id))),
      organization = organizations.find(organization => ticket.organization_id.exists(_.equals(organization._id)))
    ))
}
