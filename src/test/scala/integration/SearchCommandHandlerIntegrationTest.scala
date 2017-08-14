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

  test("Search Organizations") {
    val result = parseCommand("3 details=MegaCorp")

    result contains "9 Results Found" should equal(true)
    result contains "name: Enthaze" should equal(true)
    result contains "details: MegaCorp" should equal(true)
  }

}
