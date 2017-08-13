package com.codetest.service.organization

import com.codetest.model.Organization
import com.codetest.util.ErrorOps._
import io.circe.generic.auto._
import io.circe.parser.decode

object OrganizationParser {

  def apply(jsonStr: String): Either[Error, Array[Organization]] = decode[Array[Organization]](jsonStr).left.map(_.toError)
}
