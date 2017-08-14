package com.codetest.service.organization

import com.codetest.error.SearchFieldNotFoundError
import com.codetest.model.Organization
import com.codetest.service.SearchTermValueComparator
import com.codetest.util.ErrorOr

object OrganizationTermMapping extends SearchTermValueComparator {
  def apply(term: String, value: String, organizationList: List[Organization]): ErrorOr[List[Organization]] = {
    term match {
      case "_id" => Right(organizationList.filter(t => compare(t._id, value)))
      case "url" => Right(organizationList.filter(t => compare(t.url, value)))
      case "external_id" => Right(organizationList.filter(t => compare(t.external_id, value)))
      case "name" => Right(organizationList.filter(t => compare(t.name, value)))
      case "created_at" => Right(organizationList.filter(t => compare(t.created_at, value)))
      case "details" => Right(organizationList.filter(t => compare(t.details, value)))
      case "shared_tickets" => Right(organizationList.filter(t => compare(t.shared_tickets, value)))
      case "domain_names" => Right(organizationList.filter(t => compare(t.domain_names, value)))
      case "tags" => Right(organizationList.filter(t => compare(t.tags, value)))
      case t => Left(SearchFieldNotFoundError(t))
    }
  }
}
