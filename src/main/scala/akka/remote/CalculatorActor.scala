package akka.remote

import akka.actor.Actor

class CalculatorActor extends Actor {
  override def receive = {
    case Add(n1, n2)      => sender() ! AddResult(n1, n2, n1 + n2)
    case Subtract(n1, n2) => sender() ! SubtractResult(n1, n2, n1 - n2)
  }
}
