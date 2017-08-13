package com.codetest.model

final case class UserExtension(
                            user: User,
                            organization: Option[Organization],
                            submitTicket: Array[Ticket],
                            assignTicket: Array[Ticket]
                           ) {
                           }
