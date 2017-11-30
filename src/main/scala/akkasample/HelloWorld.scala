package akkasample

import akka.actor.{Actor, ActorLogging, Props}

class HelloWorld extends Actor with ActorLogging {
  override def preStart(): Unit = {
    val greeter = context.actorOf(Props[Greeter], "greeter")
    greeter ! Greeter.Greet
  }

  override def receive = {
    case Greeter.Done =>
      log.info("获取到消息,发送关闭消息")
      context.stop(self)

  }
}
