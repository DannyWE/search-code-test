package com.codetest.model

sealed trait Command

case object ListSearchableFields extends Command
case object Introduction extends Command
case object Quit extends Command

trait SearchCommand extends Command {
  def term: String
  def value: String
}
final case class SearchTickets(term: String, value: String) extends SearchCommand
final case class SearchUsers(term: String, value: String) extends SearchCommand
final case class SearchOrganizations(term: String, value: String) extends SearchCommand
