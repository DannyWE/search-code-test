package com.codetest.service

trait SearchTermValueComparator {

  def compare[T](option: Option[T], value: String): Boolean = {
    option match {
      case None if value.isEmpty => true
      case Some(t) => compare(t, value)
      case _ => false
    }
  }

  def compare[T](t: T, value: String): Boolean = {
    t match {
      case true if "true".equalsIgnoreCase(value) => true
      case false if "false".equalsIgnoreCase(value) => true
      case t: String if t.equals(value) => true
      case t: Long if t.toString.equals(value) => true
      case t: List[String] if t.contains(value) || (t.isEmpty && value.isEmpty) => true
      case _ => false
    }
  }
}
