package com.codetest.model

final case class UserExtension(
                            user: User,
                            organization: Option[Organization],
                            submitTicket: List[Ticket],
                            assignTicket: List[Ticket]
                           ) {
                           }
