package com.codetest.util

import scala.reflect.runtime.universe._

object GetFields {
  def apply[T: TypeTag]: List[String] = typeOf[T].members.collect {
    case m: MethodSymbol if m.isCaseAccessor => m
  }.toList.map(_.name.toString)
}
