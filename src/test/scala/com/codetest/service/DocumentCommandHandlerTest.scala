package com.codetest.service

import com.codetest.model.{Command, Introduction, ListSearchableFields, Quit}
import infrastructure.BaseTestSuite

class DocumentCommandHandlerTest extends BaseTestSuite {

  val table = Table(
    ("Command",            "Expected: is right value"),

    (ListSearchableFields,   true),
    (Introduction,           true),
    (Quit,                   false)
  )

  test("should proceed with correct command") {
    forAll(table)((command: Command,
                   expected: Boolean) => {
      DocumentCommandHandler(command).isRight should equal(expected)
    })
  }
}
