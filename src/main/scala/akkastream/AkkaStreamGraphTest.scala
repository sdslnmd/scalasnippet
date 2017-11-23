package akkastream

import akka.{Done, NotUsed}
import akka.stream.{ClosedShape, KillSwitches, UniqueKillSwitch}
import akka.stream.scaladsl.{Broadcast, Flow, GraphDSL, Keep, RunnableGraph, Sink, Source}

import scala.concurrent.Future

final case class Auther(handler: String)

final case class Hashtag(name: String)

final case class Tweet(auther: Auther, timestamp: Long, body: String) {
  def hashtags: Set[Hashtag] =
    body
      .split(" ")
      .collect {
        case t if t.startsWith("#") => Hashtag(t.replaceAll("[^#\\w]", ""))
      }
      .toSet
}

//http://www.lihaoyi.com/post/OldDesignPatternsinScala.html
//http://www.lihaoyi.com/post/ImplicitDesignPatternsinScala.html

object AkkaStreamGraphTest {

  val writeAuthors: Sink[Auther, NotUsed] = ???
  val writeHashtags: Sink[Hashtag, NotUsed] = ???
  val tweets: Source[Tweet, NotUsed] = ???

  private val graph: RunnableGraph[NotUsed] =
    RunnableGraph.fromGraph(GraphDSL.create() { implicit b =>
      import GraphDSL.Implicits._

      val bcast = b.add(Broadcast[Tweet](2))
      tweets ~> bcast.in
      bcast.out(0) ~> Flow[Tweet].map(_.auther) ~> writeAuthors
      bcast.out(1) ~> Flow[Tweet].mapConcat(_.hashtags.toList) ~> writeHashtags

      ClosedShape
    })

  //返回值不同
  private val used: NotUsed = graph.run()

}

object AkkaStreamKillSwitch {

  // 关注 toMat 方法
  private val helloStream: RunnableGraph[(UniqueKillSwitch, Future[Done])] = Source
    .single("hello world")
    .map(_.toUpperCase)
    .viaMat(KillSwitches.single)(Keep.right)
    .toMat(Sink.foreach(println))(Keep.both)


  private val (killSwitch,doneF): (UniqueKillSwitch, Future[Done]) = helloStream.run()

  killSwitch.shutdown()
  // or
  killSwitch.abort(new Exception("Exception from KillSwitch"))




}
