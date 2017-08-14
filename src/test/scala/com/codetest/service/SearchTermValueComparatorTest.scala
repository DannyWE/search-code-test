package com.codetest.service

import infrastructure.BaseTestSuite

class SearchTermValueComparatorTest extends BaseTestSuite {
  val table = Table(
    ("Field value",                  "Value to compare",  "Expected"),

    (Some(true),                      "true",             true),
    (Some(false),                     "false",            true),
    (Some(true),                      "",                 false),
    (Some("test"),                    "test",             true),
    (Some("test"),                    "no value",         false),
    (Some(100L),                      "100",              true),
    (Some(100L),                      "",                 false),
    (Some(Array("1", "2", "3")),      "3",                true),
    (Some(Array("1", "2", "3")),      "test",             false),
    (None,                            "",                 true),
    (None,                            "test",             false)
  )

  test("should compare field value") {
    forAll(table)((fieldValue: Option[Any], value: String, expectedResult: Boolean) => {
      new SearchTermValueComparator {}.compare(fieldValue, value) should equal(expectedResult)
    })
  }
}
