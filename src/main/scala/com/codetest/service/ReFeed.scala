package com.codetest.service

import com.codetest.model._

object ReFeed {
  def feedUsersExtension(tickets: Array[Ticket],
                         users: Array[User],
                         organizations: Array[Organization]): Array[UserExtension] =
    users.map(user =>
      UserExtension(
        user,
        organizations.find(t => user.organization_id.exists(_.equals(t._id))),
        tickets.filter(t => t.submitter_id.exists(_.equals(user._id))),
        tickets.filter(t => t.assignee_id.exists(_.equals(user._id)))
      )
    )

  def feedTicketsExtension(tickets: Array[Ticket],
                           users: Array[User],
                           organizations: Array[Organization]): Array[TicketExtension] =
    tickets.map(ticket => TicketExtension(
      ticket = ticket,
      submitter = users.find(user => ticket.submitter_id.exists(_.equals(user._id))),
      assigner = users.find(user => ticket.assignee_id.exists(_.equals(user._id))),
      organization = organizations.find(organization => ticket.organization_id.exists(_.equals(organization._id)))
    ))
}
