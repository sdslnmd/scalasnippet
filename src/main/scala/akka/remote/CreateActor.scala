package akka.remote

import akka.actor.{Actor, Props}

class CreateActor extends Actor {

  override def receive = {

    case op: MathOp =>
      val calculator = context.actorOf(Props[CalculatorActor])
      calculator ! op

    case result: MathResult =>
      result match {
        case MultiplyResult(n1, n2, r) => context.stop(sender())
        case DivideResult(n1, n2, r)   => context.stop(sender())
      }

  }
}
