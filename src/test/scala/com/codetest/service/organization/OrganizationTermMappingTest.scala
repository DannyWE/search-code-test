package com.codetest.service.organization

import com.codetest.model.Organization
import infrastructure.BaseTestSuite
import infrastructure.builder.defaultOrganization

class OrganizationTermMappingTest extends BaseTestSuite {

  val table = Table(
    ("Term",            "Value",                    "Organization",                                       "Expected Result"),

    ("_id",             "1",                         List(defaultOrganization),                                    true ),
    ("_id",             "some wrong value",          List(defaultOrganization),                                    false ),

    ("url",             "organization url",          List(defaultOrganization),                                    true ),
    ("url",             "some wrong value",          List(defaultOrganization),                                    false ),

    ("external_id",     "organization external id",  List(defaultOrganization),                                    true ),
    ("external_id",     "some wrong value",          List(defaultOrganization),                                    false ),

    ("name",             "organization name",        List(defaultOrganization),                                    true ),
    ("name",             "some wrong value",         List(defaultOrganization),                                    false ),

    ("created_at",       "organization created_at",  List(defaultOrganization),                                    true ),
    ("created_at",       "some wrong value",         List(defaultOrganization),                                    false ),

    ("details",          "organization details",     List(defaultOrganization),                                    true ),
    ("details",          "some wrong value",         List(defaultOrganization),                                    false ),

    ("shared_tickets",   "false",                    List(defaultOrganization),                                    true ),
    ("shared_tickets",   "some wrong value",         List(defaultOrganization),                                    false ),

    ("domain_names",     "name",                     List(defaultOrganization.copy(domain_names = List("name"))),  true ),
    ("domain_names",     "some wrong value",         List(defaultOrganization),                                    false ),

    ("tags",             "tag 3",                    List(defaultOrganization.copy(tags = List("tag 3"))),        true ),
    ("tags",             "some wrong value",         List(defaultOrganization),                                    false )
  )

  test("should map the data") {
    forAll(table)((term: String, value: String, organizations: List[Organization], expectedFound: Boolean) => {
      val result = OrganizationTermMapping.map(term, value, organizations) match {
        case Right(t) if t.nonEmpty => true
        case _ => false
      }

      result should equal(expectedFound)
    })
  }
}
