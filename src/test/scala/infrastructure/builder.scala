package infrastructure

import com.codetest.model.{Organization, Ticket, User}

object builder {
  val defaultUser = User(
    _id = 0,
    url = "user url",
    external_id = "user external_id",
    name = "user name",
    created_at = "user created_at",
    last_login_at = "user last_login_at",
    phone = "phone",
    signature = "signature",
    tags = Nil,
    role = "role",
    active = false,
    shared = false,
    suspended = false,
    verified = None,
    alias = None,
    timezone = None,
    organization_id = None,
    locale = None,
    email = None
  )

  val defaultOrganization = Organization(
    _id = 1,
    url = "organization url",
    external_id = "organization external id",
    name = "organization name",
    created_at = "organization created_at",
    details = "organization details",
    shared_tickets = false,
    domain_names = Nil,
    tags = Nil
  )

  val defaultTicket = Ticket(
    _id = "ticket id",
    url = "ticket url",
    external_id = "ticket external_id",
    created_at = "ticket created_at",
    subject = "ticket subject",
    priority = "ticket priority",
    status = "ticket status",
    submitter_id = None,
    tags = Nil,
    has_incidents = false,
    via = "via",
    `type` = None,
    organization_id = None,
    assignee_id = None,
    due_at = None,
    description = None
  )
}
