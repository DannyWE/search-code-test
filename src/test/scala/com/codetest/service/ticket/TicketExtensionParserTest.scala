package com.codetest.service.ticket

import com.codetest.util.GetResourceContent
import infrastructure.BaseTestSuite

class TicketExtensionParserTest extends BaseTestSuite {

  test("should parse tickets extension") {
    val ticketsJsonStr = GetResourceContent("/outputs/ticketsExtension.json").right.get
    val errorOrTickets = TicketExtensionParser(ticketsJsonStr)

    val tickets = errorOrTickets.right.get
    tickets.length should equal(200)

    val firstTicket = tickets.head.ticket
    firstTicket._id should equal("436bf9b0-1147-4c0a-8439-6f79833bff5b")
    firstTicket.url should equal("http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json")
    firstTicket.external_id should equal("9210cdc9-4bee-485f-a078-35396cd74063")
    firstTicket.created_at should equal("2016-04-28T11:19:34 -10:00")
    firstTicket.`type` should equal(Some("incident"))
    firstTicket.subject should equal("A Catastrophe in Korea (North)")
    firstTicket.description should equal(Some("Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum."))
    firstTicket.priority should equal("high")
    firstTicket.status should equal("pending")
    firstTicket.submitter_id should equal(Some(38))
    firstTicket.assignee_id should equal(Some(24))
    firstTicket.organization_id should equal(Some(116))
    firstTicket.tags should equal(
      List(
        "Ohio",
        "Pennsylvania",
        "American Samoa",
        "Northern Mariana Islands"
      ))
    firstTicket.has_incidents should equal(false)
    firstTicket.due_at should equal(Some("2016-07-31T02:37:50 -10:00"))
    firstTicket.via should equal("web")

    tickets.head.submitter.get._id should equal(38)
    tickets.head.assigner.get._id should equal(24)
    tickets.head.organization.get._id should equal(116)
  }
}
