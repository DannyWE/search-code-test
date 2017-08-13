package com.codetest.model

final case class Ticket(
                         _id: String,
                         url: String,
                         external_id: String,
                         created_at: String,
                         subject: String,
                         priority: String,
                         status: String,
                         tags: Array[String],
                         has_incidents: Boolean,
                         via: String,
                         `type`: Option[String],
                         submitter_id: Option[Long],
                         organization_id: Option[Long],
                         assignee_id: Option[Long],
                         due_at: Option[String],
                         description: Option[String]
                       ) {
}
