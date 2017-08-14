package com.codetest.service.ticket

import com.codetest.error.SearchFieldNotFoundError
import com.codetest.model.TicketExtension
import com.codetest.service.SearchTermValueComparator
import com.codetest.util.ErrorOr

object TicketTermMapping extends SearchTermValueComparator {

  def apply(term: String, value: String, ticketList: List[TicketExtension]): ErrorOr[List[TicketExtension]] = {
    term match {
      case "_id" => Right(ticketList.filter(t => compare(t.ticket._id, value)))
      case "url" => Right(ticketList.filter(t => compare(t.ticket.url, value)))
      case "external_id" => Right(ticketList.filter(t => compare(t.ticket.external_id, value)))
      case "created_at" => Right(ticketList.filter(t => compare(t.ticket.created_at, value)))
      case "type" => Right(ticketList.filter(t => compare(t.ticket.`type`, value)))
      case "subject" => Right(ticketList.filter(t => compare(t.ticket.subject, value)))
      case "description" => Right(ticketList.filter(t => compare(t.ticket.description, value)))
      case "priority" => Right(ticketList.filter(t => compare(t.ticket.priority, value)))
      case "status" => Right(ticketList.filter(t => compare(t.ticket.status, value)))
      case "submitter_id" => Right(ticketList.filter(t => compare(t.ticket.submitter_id, value)))
      case "assignee_id" => Right(ticketList.filter(t => compare(t.ticket.assignee_id, value)))
      case "organization_id" => Right(ticketList.filter(t => compare(t.ticket.organization_id, value)))
      case "tags" => Right(ticketList.filter(t => compare(t.ticket.tags, value)))
      case "has_incidents" => Right(ticketList.filter(t => compare(t.ticket.has_incidents, value)))
      case "due_at" => Right(ticketList.filter(t => compare(t.ticket.due_at, value)))
      case "via" => Right(ticketList.filter(t => compare(t.ticket.via, value)))
      case t => Left(SearchFieldNotFoundError(t))
    }
  }
}
