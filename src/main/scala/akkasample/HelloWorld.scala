package akkasample

import akka.actor.{Actor, Props}

class HelloWorld extends Actor {
  override def preStart(): Unit = {
    val greeter = context.actorOf(Props[Greeter], "greeter")
    greeter ! Greeter.Greet
  }

  override def receive = {
    case Greeter.Done => context.stop(self)
  }
}
