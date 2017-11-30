package akkasample

import akka.actor.{
  Actor,
  ActorLogging,
  ActorRef,
  ActorSystem,
  Props,
  Terminated
}

object MainSample {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("hello")
    val helloWorld = system.actorOf(Props[HelloWorld], "helloWorld")
    system.actorOf(Props(classOf[Terminator], helloWorld), "terminator") //带参数的actor 创建方法

  }

  class Terminator(ref: ActorRef) extends Actor with ActorLogging {
    override def receive: Receive = {
      case Terminated(_) =>
        log.info("{} 中断了.", ref.path)
        context.system.terminate()
    }
  }

}
