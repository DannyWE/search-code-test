package com.codetest.service.user

import com.codetest.model.User
import com.codetest.util.ErrorOps._
import io.circe.generic.auto._
import io.circe.parser.decode

object UserParser {

  def apply(jsonStr: String): Either[Error, Array[User]] = decode[Array[User]](jsonStr).left.map(_.toError)

}
