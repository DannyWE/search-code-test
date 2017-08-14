package com.codetest.error

final case class SearchFieldNotFoundError(invalidField: String) extends Error {
  override def getMessage: String = {
    s"""
       | Invalid search field not found : $invalidField
    """.stripMargin
  }
}