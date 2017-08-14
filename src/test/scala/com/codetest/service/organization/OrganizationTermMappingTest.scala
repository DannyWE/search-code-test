package com.codetest.service.organization

import com.codetest.model.Organization
import infrastructure.BaseTestSuite
import infrastructure.builder.organization

class OrganizationTermMappingTest extends BaseTestSuite {

  val table = Table(
    ("Term",            "Value",                    "Organization",                                       "Expected Result"),

    ("_id",             "1",                         Array(organization),                                    true ),
    ("_id",             "some wrong value",          Array(organization),                                    false ),

    ("url",             "organization url",          Array(organization),                                    true ),
    ("url",             "some wrong value",          Array(organization),                                    false ),

    ("external_id",     "organization external id",  Array(organization),                                    true ),
    ("external_id",     "some wrong value",          Array(organization),                                    false ),

    ("name",             "organization name",        Array(organization),                                    true ),
    ("name",             "some wrong value",         Array(organization),                                    false ),

    ("created_at",       "organization created_at",  Array(organization),                                    true ),
    ("created_at",       "some wrong value",         Array(organization),                                    false ),

    ("details",          "user details",             Array(organization),                                    true ),
    ("details",          "some wrong value",         Array(organization),                                    false ),

    ("shared_tickets",   "false",                    Array(organization),                                    true ),
    ("shared_tickets",   "some wrong value",         Array(organization),                                    false ),

    ("domain_names",     "name",                     Array(organization.copy(domain_names = Array("name"))), true ),
    ("domain_names",     "some wrong value",         Array(organization),                                    false ),

    ("tags",             "tag 3",                    Array(organization.copy(tags = Array("tag 3"))),        true ),
    ("tags",             "some wrong value",         Array(organization),                                    false )
  )

  test("should map the data") {
    forAll(table)((term: String, value: String, organizations: Array[Organization], expectedFound: Boolean) => {
      val result = OrganizationTermMapping.map(term, value, organizations) match {
        case Right(t) if t.length >= 1 => true
        case _ => false
      }

      result should equal(expectedFound)
    })
  }
}
