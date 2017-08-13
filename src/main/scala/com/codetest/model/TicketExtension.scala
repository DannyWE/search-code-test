package com.codetest.model

final case class TicketExtension(
                              ticket: Ticket,
                              submitter: Option[User],
                              assigner: Option[User],
                              organization: Option[Organization]
                             ) {
}
