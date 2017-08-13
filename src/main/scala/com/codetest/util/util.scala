package com.codetest

package object util {
  type ErrorOr[T] = Either[Throwable, T]
}
