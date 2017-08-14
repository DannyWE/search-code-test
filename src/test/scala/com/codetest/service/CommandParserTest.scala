package com.codetest.service

import com.codetest.error.CommandNotFoundError
import com.codetest.model._
import com.codetest.util.ErrorOr
import infrastructure.BaseTestSuite

class CommandParserTest extends BaseTestSuite {
  val table = Table(
    ("Command String",                  "Expected: Command"),

    ("quit",                            Right(Quit)),
    ("help",                            Right(Help)),
    ("view",                            Right(ListSearchableFields)),
    ("1 url=someUrl",                   Right(SearchTickets("url", "someUrl"))),
    ("2 name=name",                     Right(SearchUsers("name", "name"))),
    ("3 external_id=externalId",        Right(SearchOrganizations("external_id", "externalId"))),
    ("1 invalid=value",                 Left(CommandNotFoundError("1 invalid=value"))),
    ("invalid command",                 Left(CommandNotFoundError("invalid command")))
  )

  forAll(table)((commandStr: String, expectedCommand: ErrorOr[Command]) => {
    CommandParser.parse(commandStr) should equal(expectedCommand)
  })
}
