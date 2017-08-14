package com.codetest.service

import com.codetest.model.Organization

object Display {
  def forOrganization(organizations: List[Organization]): String = {
    organizations match {
      case Nil => "No Results Found"
      case head :: Nil =>
        s"""
           |1 Result Found
           |${forOneOrganization(head)}
         """.stripMargin
      case list =>
        s"""
           |${list.length} Results Found
           |${organizations.map(forOneOrganization)}
         """.stripMargin
    }
  }

  private def forOneOrganization(organization: Organization): String = {
    s"""
       |_id: ${organization._id}
       |name: ${organization.name}
       |details: ${organization.details}
        """.stripMargin
  }
}
