package com.codetest.service.user

import com.codetest.util.GetResourceContent
import infrastructure.BaseTestSuite

class UserParserTest extends BaseTestSuite {

  test("should parse user json files") {
    val userJsonStr = GetResourceContent("/users.json").right.get
    val errorOrUsers = UserParser(userJsonStr)

    val users = errorOrUsers.right.get
    users.length should equal(75)

    val firstUser = users.head
    firstUser._id should equal(1)
    firstUser.url should equal("http://initech.zendesk.com/api/v2/users/1.json")
    firstUser.external_id should equal("74341f74-9c79-49d5-9611-87ef9b6eb75f")
    firstUser.name should equal("Francisca Rasmussen")
    firstUser.created_at should equal("2016-04-15T05:19:46 -10:00")
    firstUser.last_login_at should equal("2013-08-04T01:03:27 -10:00")
    firstUser.phone should equal("8335-422-718")
    firstUser.signature should equal("Don't Worry Be Happy!")
    firstUser.tags should equal(List(
      "Springville",
      "Sutton",
      "Hartsville/Hartley",
      "Diaperville")
    )
    firstUser.suspended should equal(true)
    firstUser.role should equal("admin")
    firstUser.active should equal(true)
    firstUser.shared should equal(false)
    firstUser.verified should equal(Some(true))
    firstUser.alias should equal(Some("Miss Coffey"))
    firstUser.timezone should equal(Some("Sri Lanka"))
    firstUser.organization_id should equal(Some(119))
    firstUser.locale should equal(Some("en-AU"))
    firstUser.email should equal(Some("coffeyrasmussen@flotonic.com"))
  }
}
