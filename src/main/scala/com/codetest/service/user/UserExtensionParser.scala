package com.codetest.service.user

import com.codetest.model.UserExtension
import com.codetest.util.ErrorOps._
import io.circe.generic.auto._
import io.circe.parser.decode

object UserExtensionParser {
  def apply(jsonStr: String): Either[Error, List[UserExtension]] = decode[List[UserExtension]](jsonStr).left.map(_.toError)
}
