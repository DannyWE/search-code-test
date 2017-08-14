package com.codetest.service.ticket

import com.codetest.model.TicketExtension
import com.codetest.util.ErrorOps._
import io.circe.generic.auto._
import io.circe.parser.decode

object TicketExtensionParser {
  def apply(jsonStr: String): Either[Error, List[TicketExtension]] = decode[List[TicketExtension]](jsonStr).left.map(_.toError)
}
