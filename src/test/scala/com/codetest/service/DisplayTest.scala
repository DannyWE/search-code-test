package com.codetest.service

import com.codetest.model.{Organization, TicketExtension, UserExtension}
import infrastructure.BaseTestSuite
import infrastructure.builder._

class DisplayTest extends BaseTestSuite {

  val table = Table(
    ("Model",                                                             "Expected: contain"),

    (List(defaultOrganization),                                           "1 Result Found"),
    (List(defaultOrganization, defaultOrganization, defaultOrganization), "3 Results Found"),
    (List.empty[Organization],                                            "No Results Found")
  )

  test("should contain correct display value") {
    forAll(table)((organizations: List[Organization],
                   expectedContainValue: String) => {

      Display.forModel(organizations, Display.forOrganization).contains(expectedContainValue) should equal(true)
    })
  }

  test("should display one organization") {
    val expectedStr = s"""
                         |_id: 1
                         |name: organization name
                         |details: organization details
       """.stripMargin

    val result = Display.forOrganization(defaultOrganization)

    result contains expectedStr should equal(true)
  }

  test("should display one user") {
    val expectedStr = s"""
                         |_id: 0
                         |active: false
                         |email: email
                         |submitted ticket number: 0
                         |assigned ticket number: 0
                         |organization name: organization
      """.stripMargin

    val result = Display.forUser(UserExtension(defaultUser.copy(email = Some("email")),
      Some(defaultOrganization.copy(name = "organization")), Nil, Nil))

    result contains expectedStr should equal(true)
  }

  test("should display one ticket") {
    val expectedStr =
      s"""
         |_id: ticket id
         |status: ticket status
         |tags: tag 1, tag 2, tag 3
       """.stripMargin

    val result = Display.forTicket(TicketExtension(defaultTicket
      .copy(tags = List("tag 1", "tag 2", "tag 3")), None, None, None))

    result contains expectedStr should equal(true)
  }
}
