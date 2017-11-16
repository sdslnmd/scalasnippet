package akkastream

import akka.NotUsed
import akka.stream.scaladsl.Source

object AkkaStreamTest {
}

object AkkaMainTest extends App{

  val source: Source[Int, NotUsed] = Source(1 to 100)
  source.runForeach(x => println(x))(materializer)


}



