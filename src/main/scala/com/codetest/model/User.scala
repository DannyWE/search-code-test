package com.codetest.model

final case class User(_id: Long,
                      url: String,
                      external_id: String,
                      name: String,
                      created_at: String,
                      last_login_at: String,
                      phone: String,
                      signature: String,
                      tags: List[String],
                      role: String,
                      active: Boolean,
                      shared: Boolean,
                      suspended: Boolean,
                      verified: Option[Boolean],
                      alias: Option[String],
                      timezone: Option[String],
                      organization_id: Option[Long],
                      locale: Option[String],
                      email: Option[String]
                      ) {
}
