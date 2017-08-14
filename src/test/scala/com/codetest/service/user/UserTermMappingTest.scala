package com.codetest.service.user

import com.codetest.model.UserExtension
import infrastructure.BaseTestSuite
import infrastructure.builder.defaultUser

class UserTermMappingTest extends BaseTestSuite {

  val table = Table(
    ("Term",            "Value",                       "Users",                                        "Expected Result"),

    ("_id",             "0",                          List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("_id",             "some wrong value",           List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("url",             "user url",                   List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("url",             "some wrong value",           List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("external_id",     "user external_id",           List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("external_id",     "some wrong value",           List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("name",            "user name",                  List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("name",            "some wrong value",           List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("created_at",       "user created_at",           List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("created_at",       "some wrong value",          List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("last_login_at",    "user last_login_at",        List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("last_login_at",    "some wrong value",          List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("phone",            "phone",                     List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("phone",            "some wrong value",          List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("signature",        "signature",                 List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("signature",        "some wrong value",          List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("tags",             "tag 3",                     List(UserExtension(defaultUser.copy(tags = List("tag 1", "tag 2", "tag 3")), None, Nil, Nil)),    true ),
    ("tags",             "",                          List(UserExtension(defaultUser.copy(tags = Nil), None, Nil, Nil)),    true ),
    ("tags",             "some wrong value",          List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("role",             "role",                      List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("role",             "some wrong value",          List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("active",           "false",                     List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("active",           "some wrong value",          List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("shared",           "false",                     List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("shared",           "some wrong value",          List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("suspended",         "false",                    List(UserExtension(defaultUser, None, Nil, Nil)),    true ),
    ("suspended",        "some wrong value",          List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("verified",          "true",                     List(UserExtension(defaultUser.copy(verified = Some(true)), None, Nil, Nil)),    true ),
    ("verified",          "some wrong value",         List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("alias",             "some alias",               List(UserExtension(defaultUser.copy(alias = Some("some alias")), None, Nil, Nil)),    true ),
    ("alias",             "some wrong value",         List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("timezone",          "some timezone",            List(UserExtension(defaultUser.copy(timezone = Some("some timezone")), None, Nil, Nil)),    true ),
    ("timezone",          "some wrong value",         List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("organization_id",    "300",                     List(UserExtension(defaultUser.copy(organization_id = Some(300)), None, Nil, Nil)),    true ),
    ("organization_id",    "some wrong value",        List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("locale",             "locale",                  List(UserExtension(defaultUser.copy(locale = Some("locale")), None, Nil, Nil)),    true ),
    ("locale",             "some wrong value",        List(UserExtension(defaultUser, None, Nil, Nil)),    false ),

    ("email",             "some email",               List(UserExtension(defaultUser.copy(email = Some("some email")), None, Nil, Nil)),    true ),
    ("email",             "some wrong value",         List(UserExtension(defaultUser, None, Nil, Nil)),    false )
  )

  test("should map the data") {
    forAll(table)((term: String, value: String, users: List[UserExtension], expectedFound: Boolean) => {
      val result = UserTermMapping.map(term, value, users) match {
        case Right(t) if t.nonEmpty => true
        case _ => false
      }

      result should equal(expectedFound)
    })
  }
}
