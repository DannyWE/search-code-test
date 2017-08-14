package com.codetest.service.user

import com.codetest.util.GetResourceContent
import infrastructure.BaseTestSuite

class UserExtensionParserTest extends BaseTestSuite {

  test("should parse user extension") {
    val usersJsonStr = GetResourceContent("/outputs/usersExtension.json").right.get
    val errorOrUsers = UserExtensionParser(usersJsonStr)

    val users = errorOrUsers.right.get
    users.length should equal(75)

    users.head.user.email should equal(Some("coffeyrasmussen@flotonic.com"))
    users.head.assignTicket.length should equal(2)
    users.head.submitTicket.length should equal(2)
    users.head.organization.get.tags should equal(List("Erickson",
      "Mccoy",
      "Wiggins",
      "Brooks"
    ))
  }
}
