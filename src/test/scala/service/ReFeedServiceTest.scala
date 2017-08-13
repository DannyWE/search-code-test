package service

import com.codetest.model._
import com.codetest.service.ReFeed
import infrastructure.BaseTestSuite
import infrastructure.builder._

class ReFeedServiceTest extends BaseTestSuite {

  val specialUser: User = user.copy(organization_id = Some(100)).copy(_id = 300)
  val linkedOrganization: Organization = organization.copy(_id = 100)
  val submitTicket: Ticket = ticket.copy(submitter_id = Some(300))
  val assignedTicket: Ticket = ticket.copy(assignee_id = Some(300))

  val usersExtensionTable = Table(
    ("Tickets",                              "Users",                                                 "Organizations",                 "Expected: UserExtend"),

    (Array[Ticket](),                       Array(user.copy(organization_id = Some(100))),            Array(linkedOrganization),      (Some(linkedOrganization), 0, 0)),
    (Array(submitTicket),                   Array(user.copy(_id = 300)),                              Array[Organization](),          (None, 1, 0)),
    (Array(assignedTicket),                 Array(user.copy(_id = 300)),                              Array[Organization](),          (None, 0, 1)),
    (Array(submitTicket, assignedTicket),   Array(user.copy(_id = 300, organization_id = Some(100))), Array(linkedOrganization),      (Some(linkedOrganization), 1, 1))
  )

  test("should re-feed to User Organization which combines User and Organization") {
    forAll(usersExtensionTable)((tickets: Array[Ticket],
                                 users: Array[User],
                                 organizations: Array[Organization],
                                 expected: (Option[Organization], Int, Int)) => {
      val user = ReFeed.feedUsersExtension(tickets, users, organizations).head
      user.organization should equal(expected._1)
      user.submitTicket.length should equal(expected._2)
      user.assignTicket.length should equal(expected._3)
    })
  }

  val submitter: User = user.copy(_id = 100)
  val assignee: User = user.copy(_id = 1000)
  val ticketOrganization: Organization = organization.copy(_id = 100)
  val ticketsExtensionTable = Table(
    ("Ticket",                                   "Users",                    "Organizations",             "Expected: UserExtend"),

    (Array(ticket),                              Array(user),                Array(organization),         Array(TicketExtension(ticket, None, None, None))),

    (Array(
      ticket.copy(
        submitter_id = Some(submitter._id))
    ),                                           Array(submitter, assignee), Array(ticketOrganization),   Array(TicketExtension(ticket.copy(
                                                                                                                      submitter_id = Some(submitter._id)
                                                                                                                    ), Some(submitter), None, None))),

    (Array(
      ticket.copy(
        assignee_id = Some(assignee._id))
    ),                                           Array(submitter, assignee), Array[Organization](),       Array(TicketExtension(ticket.copy(
                                                                                                                      assignee_id = Some(assignee._id)
                                                                                                                    ), None, Some(assignee), None))),

    (Array(
      ticket.copy(
        submitter_id = Some(submitter._id),
        assignee_id = Some(assignee._id)
      )
    ),                                           Array(submitter, assignee), Array[Organization](),       Array(TicketExtension(ticket.copy(
                                                                                                                        submitter_id = Some(submitter._id),
                                                                                                                        assignee_id = Some(assignee._id)
                                                                                                                      ), Some(submitter), Some(assignee), None))),
      (Array(
        ticket.copy(
          submitter_id = Some(submitter._id),
          assignee_id = Some(assignee._id),
          organization_id = Some(ticketOrganization._id)
        )
      ),                                         Array(submitter, assignee), Array(ticketOrganization),   Array(TicketExtension(ticket.copy(
                                                                                                                    submitter_id = Some(submitter._id),
                                                                                                                    assignee_id = Some(assignee._id),
                                                                                                                    organization_id = Some(ticketOrganization._id)
                                                                                                                  ), Some(submitter), Some(assignee), Some(ticketOrganization))))
  )

  test("should re-feed to Ticket extension which combines Ticket, User and Organization") {
    forAll(ticketsExtensionTable)((ticketArray: Array[Ticket],
                                   userArray: Array[User],
                                   organizationArray: Array[Organization],
                                   expectedTickets: Array[TicketExtension]) => {
      ReFeed.feedTicketsExtension(ticketArray, userArray, organizationArray) should equal(expectedTickets)
    })
  }
}
