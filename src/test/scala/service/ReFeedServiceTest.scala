package service

import com.codetest.model._
import com.codetest.service.ReFeed
import infrastructure.BaseTestSuite
import infrastructure.builder._

class ReFeedServiceTest extends BaseTestSuite {

  val specialUser: User = user.copy(organization_id = Some(100)).copy(_id = 300)
  val linkedOrganization: Organization = defaultOrganization.copy(_id = 100)
  val submitTicket: Ticket = ticket.copy(submitter_id = Some(300))
  val assignedTicket: Ticket = ticket.copy(assignee_id = Some(300))

  val usersExtensionTable = Table(
    ("Tickets",                              "Users",                                                 "Organizations",                 "Expected: UserExtend"),

    (List[Ticket](),                       List(user.copy(organization_id = Some(100))),            List(linkedOrganization),      (Some(linkedOrganization), 0, 0)),
    (List(submitTicket),                   List(user.copy(_id = 300)),                              List[Organization](),          (None, 1, 0)),
    (List(assignedTicket),                 List(user.copy(_id = 300)),                              List[Organization](),          (None, 0, 1)),
    (List(submitTicket, assignedTicket),   List(user.copy(_id = 300, organization_id = Some(100))), List(linkedOrganization),      (Some(linkedOrganization), 1, 1))
  )

  test("should re-feed to User Organization which combines User and Organization") {
    forAll(usersExtensionTable)((tickets: List[Ticket],
                                 users: List[User],
                                 organizations: List[Organization],
                                 expected: (Option[Organization], Int, Int)) => {
      val user = ReFeed.feedUsersExtension(tickets, users, organizations).head
      user.organization should equal(expected._1)
      user.submitTicket.length should equal(expected._2)
      user.assignTicket.length should equal(expected._3)
    })
  }

  val submitter: User = user.copy(_id = 100)
  val assignee: User = user.copy(_id = 1000)
  val ticketOrganization: Organization = defaultOrganization.copy(_id = 100)
  val ticketsExtensionTable = Table(
    ("Ticket",                                   "Users",                    "Organizations",             "Expected: UserExtend"),

    (List(ticket),                              List(user),                List(defaultOrganization),         List(TicketExtension(ticket, None, None, None))),

    (List(
      ticket.copy(
        submitter_id = Some(submitter._id))
    ),                                           List(submitter, assignee), List(ticketOrganization),   List(TicketExtension(ticket.copy(
                                                                                                                      submitter_id = Some(submitter._id)
                                                                                                                    ), Some(submitter), None, None))),

    (List(
      ticket.copy(
        assignee_id = Some(assignee._id))
    ),                                           List(submitter, assignee), List[Organization](),       List(TicketExtension(ticket.copy(
                                                                                                                      assignee_id = Some(assignee._id)
                                                                                                                    ), None, Some(assignee), None))),

    (List(
      ticket.copy(
        submitter_id = Some(submitter._id),
        assignee_id = Some(assignee._id)
      )
    ),                                           List(submitter, assignee), List[Organization](),       List(TicketExtension(ticket.copy(
                                                                                                                        submitter_id = Some(submitter._id),
                                                                                                                        assignee_id = Some(assignee._id)
                                                                                                                      ), Some(submitter), Some(assignee), None))),
      (List(
        ticket.copy(
          submitter_id = Some(submitter._id),
          assignee_id = Some(assignee._id),
          organization_id = Some(ticketOrganization._id)
        )
      ),                                         List(submitter, assignee), List(ticketOrganization),   List(TicketExtension(ticket.copy(
                                                                                                                    submitter_id = Some(submitter._id),
                                                                                                                    assignee_id = Some(assignee._id),
                                                                                                                    organization_id = Some(ticketOrganization._id)
                                                                                                                  ), Some(submitter), Some(assignee), Some(ticketOrganization))))
  )

  test("should re-feed to Ticket extension which combines Ticket, User and Organization") {
    forAll(ticketsExtensionTable)((ticketList: List[Ticket],
                                   userList: List[User],
                                   organizationList: List[Organization],
                                   expectedTickets: List[TicketExtension]) => {
      ReFeed.feedTicketsExtension(ticketList, userList, organizationList) should equal(expectedTickets)
    })
  }
}
