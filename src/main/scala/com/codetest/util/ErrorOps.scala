package com.codetest.util

object ErrorOps {
  implicit class ConvertError(circeError: io.circe.Error) {
    def toError: Error = {
      new Error(circeError.toString, circeError)
    }
  }
}