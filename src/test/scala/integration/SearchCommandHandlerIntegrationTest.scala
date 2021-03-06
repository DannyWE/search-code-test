package integration

import com.codetest.service.{CommandParser, SearchCommandHandler}
import infrastructure.BaseTestSuite

class SearchCommandHandlerIntegrationTest extends BaseTestSuite {

  def parseCommand: String => String = line => {
    (for {
      command <- CommandParser.parse(line)
      resultStr <- SearchCommandHandler(command)
    } yield resultStr)
      .fold(
        e => e.getMessage,
        t => t
      )
  }

  test("Search Tickets") {
    val commands: List[String] = List("1 invalid-command", "1 priority=invalid", "1 priority=normal")

    val result = commands.foldLeft("")((acc, ele) => acc + parseCommand(ele))

    result contains "Invalid command : 1 invalid-command" should equal(true)
    result contains "No Results Found" should equal(true)
    result contains "45 Results Found" should equal(true)
  }

  test("Search Organizations") {
    val result = parseCommand("3 details=MegaCorp")

    result contains "9 Results Found" should equal(true)
    result contains "name: Enthaze" should equal(true)
    result contains "details: MegaCorp" should equal(true)
  }

  test("Search Users") {
    val result = parseCommand("2 name=Sweet Cain")

    result contains "1 Result Found" should equal(true)
    result contains "active: true" should equal(true)
    result contains "organization name: Zentry" should equal(true)
  }
}
