package com.codetest.service

import com.codetest.model.Organization
import infrastructure.BaseTestSuite
import infrastructure.builder.defaultOrganization

class DisplayTest extends BaseTestSuite {

  val organizationTable = Table(
    ("Organizations",                                                    "Expected: contain"),

    (List(defaultOrganization),                                           "1 Result Found"),
    (List(defaultOrganization, defaultOrganization, defaultOrganization), "3 Results Found"),
    (List.empty[Organization],                                            "No Results Found")
  )

  test("should contain correct display value") {
    forAll(organizationTable)((organizations: List[Organization],
                               expectedContainValue: String) => {

      Display.forOrganization(organizations).contains(expectedContainValue) should equal(true)
    })
  }
}
