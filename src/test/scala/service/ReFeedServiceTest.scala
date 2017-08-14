package service

import com.codetest.model._
import com.codetest.service.ReFeed
import infrastructure.BaseTestSuite
import infrastructure.builder._

class ReFeedServiceTest extends BaseTestSuite {

  val specialUser: User = defaultUser.copy(organization_id = Some(100)).copy(_id = 300)
  val linkedOrganization: Organization = defaultOrganization.copy(_id = 100)
  val submitTicket: Ticket = defaultTicket.copy(submitter_id = Some(300))
  val assignedTicket: Ticket = defaultTicket.copy(assignee_id = Some(300))

  val usersExtensionTable = Table(
    ("Tickets",                              "Users",                                                 "Organizations",                 "Expected: UserExtend"),

    (List[Ticket](),                       List(defaultUser.copy(organization_id = Some(100))),            List(linkedOrganization),      (Some(linkedOrganization), 0, 0)),
    (List(submitTicket),                   List(defaultUser.copy(_id = 300)),                              List[Organization](),          (None, 1, 0)),
    (List(assignedTicket),                 List(defaultUser.copy(_id = 300)),                              List[Organization](),          (None, 0, 1)),
    (List(submitTicket, assignedTicket),   List(defaultUser.copy(_id = 300, organization_id = Some(100))), List(linkedOrganization),      (Some(linkedOrganization), 1, 1))
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

  val submitter: User = defaultUser.copy(_id = 100)
  val assignee: User = defaultUser.copy(_id = 1000)
  val ticketOrganization: Organization = defaultOrganization.copy(_id = 100)
  val ticketsExtensionTable = Table(
    ("Ticket",                                   "Users",                    "Organizations",             "Expected: UserExtend"),

    (List(defaultTicket),                        List(defaultUser),           List(defaultOrganization),    List(TicketExtension(defaultTicket, None, None, None))),

    (List(
      defaultTicket.copy(
        submitter_id = Some(submitter._id))
    ),                                           List(submitter, assignee),   List(ticketOrganization),     List(TicketExtension(defaultTicket.copy(
                                                                                                                      submitter_id = Some(submitter._id)
                                                                                                                    ), Some(submitter), None, None))),

    (List(
      defaultTicket.copy(
        assignee_id = Some(assignee._id))
    ),                                           List(submitter, assignee),   List[Organization](),         List(TicketExtension(defaultTicket.copy(
                                                                                                                      assignee_id = Some(assignee._id)
                                                                                                                    ), None, Some(assignee), None))),

    (List(
      defaultTicket.copy(
        submitter_id = Some(submitter._id),
        assignee_id = Some(assignee._id)
      )
    ),                                           List(submitter, assignee),   List[Organization](),         List(TicketExtension(defaultTicket.copy(
                                                                                                                        submitter_id = Some(submitter._id),
                                                                                                                        assignee_id = Some(assignee._id)
                                                                                                                      ), Some(submitter), Some(assignee), None))),
      (List(
        defaultTicket.copy(
          submitter_id = Some(submitter._id),
          assignee_id = Some(assignee._id),
          organization_id = Some(ticketOrganization._id)
        )
      ),                                         List(submitter, assignee), List(ticketOrganization),        List(TicketExtension(defaultTicket.copy(
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
