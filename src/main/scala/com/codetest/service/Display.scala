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
        val moreDetails = organizations.map(t => {
          s"""
             |_id: ${t._id}
             |name: ${t.name}
             |details: ${t.details}
        """.stripMargin
        }).mkString("")

        s"""
           |${list.length} Results Found
           |$moreDetails
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
