package com.codetest.service.user

import com.codetest.error.SearchFieldNotFoundError
import com.codetest.model.UserExtension
import com.codetest.service.SearchTermValueComparator
import com.codetest.util.ErrorOr

object UserTermMapping extends SearchTermValueComparator {
  def map(term: String, value: String, users: List[UserExtension]): ErrorOr[List[UserExtension]] = {
    term match {
      case "_id" => Right(users.filter(t => compare(t.user._id, value)))
      case "url" => Right(users.filter(t => compare(t.user.url, value)))
      case "external_id" => Right(users.filter(t => compare(t.user.external_id, value)))
      case "name" => Right(users.filter(t => compare(t.user.name, value)))
      case "created_at" => Right(users.filter(t => compare(t.user.created_at, value)))
      case "last_login_at" => Right(users.filter(t => compare(t.user.last_login_at, value)))
      case "phone" => Right(users.filter(t => compare(t.user.phone, value)))
      case "signature" => Right(users.filter(t => compare(t.user.signature, value)))
      case "tags" => Right(users.filter(t => compare(t.user.tags, value)))
      case "role" => Right(users.filter(t => compare(t.user.role, value)))
      case "active" => Right(users.filter(t => compare(t.user.active, value)))
      case "shared" => Right(users.filter(t => compare(t.user.shared, value)))
      case "suspended" => Right(users.filter(t => compare(t.user.suspended, value)))
      case "verified" => Right(users.filter(t => compare(t.user.verified, value)))
      case "alias" => Right(users.filter(t => compare(t.user.alias, value)))
      case "timezone" => Right(users.filter(t => compare(t.user.timezone, value)))
      case "organization_id" => Right(users.filter(t => compare(t.user.organization_id, value)))
      case "locale" => Right(users.filter(t => compare(t.user.locale, value)))
      case "email" => Right(users.filter(t => compare(t.user.email, value)))
      case t => Left(SearchFieldNotFoundError(t))
    }
  }
}
