package infrastructure

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class BaseTestSuite extends FunSuite with TableDrivenPropertyChecks with Matchers
