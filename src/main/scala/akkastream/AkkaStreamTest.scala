package akkastream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Keep, Sink, Source}

import scala.concurrent.ExecutionContextExecutor

object AkkaStreamTest {}

object AkkaStreamMainTest extends App {

  implicit val system: ActorSystem = ActorSystem("reactive-tweets")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  Source
    .repeat("hello world ")
    .zip(Source.fromIterator(() => Iterator.from(0)))
    .take(7)
    .map {
      case (s, n) =>
        val i = "" * n
        f"$i$n%n"
    }
    .toMat(Sink.foreach(print))(Keep.right)
    .run()
    .onComplete(_ => system.terminate())

}
