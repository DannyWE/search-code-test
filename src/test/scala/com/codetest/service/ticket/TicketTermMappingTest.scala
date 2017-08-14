package com.codetest.service.ticket

import com.codetest.model.TicketExtension
import infrastructure.BaseTestSuite
import infrastructure.builder.defaultTicket

class TicketTermMappingTest extends BaseTestSuite {

  val table = Table(
    ("Term",            "Value",               "Tickets",                                      "Expected Result"),

    ("_id",           "ticket id",            List(TicketExtension(defaultTicket, None, None, None)),    true ),
    ("_id",           "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("url",           "ticket url",           List(TicketExtension(defaultTicket, None, None, None)),    true ),
    ("url",           "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("external_id",   "ticket external_id",   List(TicketExtension(defaultTicket, None, None, None)),    true ),
    ("external_id",   "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("created_at",    "ticket created_at",    List(TicketExtension(defaultTicket, None, None, None)),    true ),
    ("created_at",    "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("subject",       "ticket subject",       List(TicketExtension(defaultTicket, None, None, None)),    true ),
    ("subject",       "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("priority",      "ticket priority",      List(TicketExtension(defaultTicket, None, None, None)),    true ),
    ("priority",      "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("status",        "ticket status",        List(TicketExtension(defaultTicket, None, None, None)),    true ),
    ("status",        "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("submitter_id",  "",                     List(TicketExtension(defaultTicket, None, None, None)),    true ),
    ("submitter_id",  "100",                  List(TicketExtension(defaultTicket.copy(submitter_id = Some(100)), None, None, None)),  true ),
    ("submitter_id",  "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("tags",          "tag3",                 List(TicketExtension(defaultTicket.copy(tags = List("tag1", "tag2", "tag3")), None, None, None)),    true ),
    ("tags",          "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("has_incidents",  "true",                List(TicketExtension(defaultTicket.copy(has_incidents = true), None, None, None)),    true ),
    ("has_incidents",  "some wrong value",    List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("via",           "via",                  List(TicketExtension(defaultTicket, None, None, None)),    true ),
    ("via",           "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("type",          "normal",               List(TicketExtension(defaultTicket.copy(`type` = Some("normal")), None, None, None)),    true ),
    ("type",          "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("organization_id",  "100",               List(TicketExtension(defaultTicket.copy(organization_id = Some(100)), None, None, None)), true ),
    ("organization_id",  "some wrong value",  List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("assignee_id",   "100",                  List(TicketExtension(defaultTicket.copy(assignee_id = Some(100)), None, None, None)),    true ),
    ("assignee_id",   "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("due_at",        "some date",            List(TicketExtension(defaultTicket.copy(due_at = Some("some date")), None, None, None)),  true ),
    ("due_at",        "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false ),

    ("description",   "description",          List(TicketExtension(defaultTicket.copy(description = Some("description")), None, None, None)), true ),
    ("description",   "some wrong value",     List(TicketExtension(defaultTicket, None, None, None)),    false )
  )

  test("should map the data") {
    forAll(table)((term: String, value: String, tickets: List[TicketExtension], expectedFound: Boolean) => {
      val result = TicketTermMapping.map(term, value, tickets) match {
        case Right(t) if t.nonEmpty => true
        case _ => false
      }

      result should equal(expectedFound)
    })
  }
}
