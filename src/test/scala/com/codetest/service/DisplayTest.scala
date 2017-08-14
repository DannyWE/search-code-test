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
                         |url: organization url
                         |external_id: organization external id
                         |name: organization name
                         |created_at: organization created_at
                         |details: organization details
                         |shared_tickets: false
                         |domain_names: domain name
                         |tags: tag
       """.stripMargin

    val result = Display.forOrganization(defaultOrganization.copy(domain_names = List("domain name"), tags = List("tag")))

    result contains expectedStr should equal(true)
  }

  test("should display one user") {
    val expectedStr = s"""
                         |_id: 0
                         |url: user url
                         |external_id: user external_id
                         |name: user name
                         |created_at: user created_at
                         |last_login_at: user last_login_at
                         |phone: phone
                         |signature: signature
                         |tags: tag
                         |role: role
                         |active: false
                         |shared: false
                         |suspended: false
                         |verified: true
                         |alias: alias
                         |timezone: timezone
                         |organization_id: 1
                         |locale: locale
                         |email: email
                         |organization name: organization
                         |submit tickets number: 1
                         |submit tickets: ticket id
                         |assign tickets number: 1
                         |assign tickets: ticket id
      """.stripMargin

    val result = Display.forUser(UserExtension(defaultUser.copy(
      email = Some("email"),
      tags = List("tag"),
      verified = Some(true),
      alias = Some("alias"),
      timezone = Some("timezone"),
      organization_id = Some(1),
      locale = Some("locale")
    ),
      Some(defaultOrganization.copy(name = "organization")), List(defaultTicket), List(defaultTicket)))

    result contains expectedStr should equal(true)
  }

  test("should display one ticket") {
    val expectedStr =
      s"""
         |_id: ticket id
         |external_id: ticket external_id
         |created_at: ticket created_at
         |subject: ticket subject
         |priority: ticket priority
         |status: ticket status
         |tags: tag 1, tag 2, tag 3
         |has_incidents: false
         |via: via
         |submitter_id: 1
         |submitter_name: user name
         |organization_id: 1
         |organization_name: organization name
         |assignee_id: 1
         |assignee_name: user name
       """.stripMargin

    val result = Display.forTicket(TicketExtension(defaultTicket
      .copy(tags = List("tag 1", "tag 2", "tag 3"),
        submitter_id = Some(1),
        assignee_id = Some(1),
        organization_id = Some(1)
      ), Some(defaultUser), Some(defaultUser), Some(defaultOrganization)))

    result contains expectedStr should equal(true)
  }
}
