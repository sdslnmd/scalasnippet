package akkastream

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ThrottleMode}
import akka.stream.scaladsl.{Keep, Sink, Source}

import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContextExecutor, Future}

object AkkaStreamTest {}

object AkkaStreamMainTest extends App {

  implicit val system: ActorSystem = ActorSystem("reactive")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  Source
    .repeat("hello world ")
    .zip(Source.fromIterator(() => Iterator.from(0)))
    .take(7)
    .map {
      case (s, n) =>
        val i = " " * n
        s"$i$s"
    }
    .throttle(42, 1.second, 1, ThrottleMode.shaping)
    .toMat(Sink.foreach(println))(Keep.right)
    .run()
    .onComplete(_ => system.terminate())

  Source(1 to 10000).mapAsync(8) { v =>
    Future {
      val start = System.currentTimeMillis()
      while ((System.currentTimeMillis() - start) < 10) {
        v
      }
    }
  }


  Source(1 to 10000).mapAsync(8) { v =>
    Future.successful {
      val start = System.currentTimeMillis()
      while ((System.currentTimeMillis() - start) < 10) {
        v
      }
    }
  }.runWith(Sink.foreach(println)).onComplete(_=>"run over")






}
