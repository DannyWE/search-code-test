package com.codetest.service.organization

import com.codetest.util.GetResourceContent
import infrastructure.BaseTestSuite

class OrganizationParserTest extends BaseTestSuite {

  test("should parse organizations json files") {
    val organizationsJsonStr = GetResourceContent("/organizations.json").right.get
    val errorOrOrganizations = OrganizationParser(organizationsJsonStr)

    val organizations = errorOrOrganizations.right.get
    organizations.length should equal(25)

    val firstOrganization = organizations.head
    firstOrganization._id should equal(101)
    firstOrganization.url should equal("http://initech.zendesk.com/api/v2/organizations/101.json")
    firstOrganization.external_id should equal("9270ed79-35eb-4a38-a46f-35725197ea8d")
    firstOrganization.name should equal("Enthaze")
    firstOrganization.created_at should equal("2016-05-21T11:10:28 -10:00")
    firstOrganization.details should equal("MegaCorp")
    firstOrganization.shared_tickets should equal(false)
    firstOrganization.domain_names should equal(List(
      "kage.com",
      "ecratic.com",
      "endipin.com",
      "zentix.com"
    ))
    firstOrganization.tags should equal(List(
      "Fulton",
      "West",
      "Rodriguez",
      "Farley"
    ))
  }
}
