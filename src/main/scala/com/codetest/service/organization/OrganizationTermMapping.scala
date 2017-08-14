package com.codetest.service.organization

import com.codetest.error.SearchFieldNotFoundError
import com.codetest.model.Organization
import com.codetest.service.SearchTermValueComparator
import com.codetest.util.ErrorOr

object OrganizationTermMapping extends SearchTermValueComparator {
  def apply(term: String, value: String, organizationArray: List[Organization]): ErrorOr[List[Organization]] = {
    term match {
      case "_id" => Right(organizationArray.filter(t => compare(t._id, value)))
      case "url" => Right(organizationArray.filter(t => compare(t.url, value)))
      case "external_id" => Right(organizationArray.filter(t => compare(t.external_id, value)))
      case "name" => Right(organizationArray.filter(t => compare(t.name, value)))
      case "created_at" => Right(organizationArray.filter(t => compare(t.created_at, value)))
      case "details" => Right(organizationArray.filter(t => compare(t.details, value)))
      case "shared_tickets" => Right(organizationArray.filter(t => compare(t.shared_tickets, value)))
      case "domain_names" => Right(organizationArray.filter(t => compare(t.domain_names, value)))
      case "tags" => Right(organizationArray.filter(t => compare(t.tags, value)))
      case t => Left(SearchFieldNotFoundError(t))
    }
  }
}
