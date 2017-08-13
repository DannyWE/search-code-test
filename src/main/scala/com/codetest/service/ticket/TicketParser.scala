package com.codetest.service.ticket

import com.codetest.model.Ticket
import com.codetest.util.ErrorOps._
import io.circe.generic.auto._
import io.circe.parser.decode

object TicketParser {

  def apply(jsonStr: String): Either[Error, Array[Ticket]] = decode[Array[Ticket]](jsonStr).left.map(_.toError)
}
