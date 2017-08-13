package integration

import infrastructure.BaseTestSuite

class SearchCommandIntegrationTest extends BaseTestSuite {

  def parseCommand: String => String = ???

  test("Search Organizations") {
    val result = parseCommand("3 details=MegaCorp")

    result contains "9 Results Found" should equal(true)
    result contains "name: Enthaze" should equal(true)
    result contains "details: MegaCorp" should equal(true)
  }

}
