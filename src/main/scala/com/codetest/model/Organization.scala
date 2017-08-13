package com.codetest.model

final case class Organization(
                          _id: Long,
                          url: String,
                          external_id: String,
                          name: String,
                          created_at: String,
                          details: String,
                          shared_tickets: Boolean,
                          domain_names: Array[String],
                          tags: Array[String]
                        ) {

}
