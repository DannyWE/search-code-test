package com.codetest.app

import com.codetest.model.{Command, Introduction, Quit}
import com.codetest.service._
import com.codetest.util.ErrorOr

object Main extends App {

  DocumentCommandHandler(Introduction).map(println)

  while(true) {
    parseAndConsumeCommand(scala.io.StdIn.readLine())
  }

  def parseAndConsumeCommand(line: String): Unit = {
    val result = for {
      command <- CommandParser.parse(line)
      resultStr <- consumeCommand(command)
    } yield resultStr

    result.left.map(e => println(e.getMessage))
  }

  private def consumeCommand(command: Command): ErrorOr[Unit] = {
    command match {
      case Quit => Right(sys.exit(0))
      case t => (SearchCommandHandler orElse DocumentCommandHandler)(t).map(println)
    }
  }
}
