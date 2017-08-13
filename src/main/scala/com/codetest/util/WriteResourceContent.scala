package com.codetest.util

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import scala.util.Try

object WriteResourceContent {
  def apply(filePath: String, content: String): Either[Throwable, Unit] = {
    Try {
      val file = this.getClass.getClassLoader.getResource(filePath).getFile
      Files.write(Paths.get(file), content.getBytes(StandardCharsets.UTF_8))
      ()
    }.toEither
  }
}
