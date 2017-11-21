package akkastream

import akka.NotUsed
import akka.stream.ClosedShape
import akka.stream.scaladsl.{Broadcast, Flow, GraphDSL, RunnableGraph, Sink, Source}

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

//  val writeAuthors: Sink[Auther, NotUsed] = ???
//  val writeHashtags: Sink[Hashtag, NotUsed] = ???
//  val tweets: Source[Tweet, NotUsed] = ???
//
//  private val graph: RunnableGraph[NotUsed] = RunnableGraph.fromGraph(GraphDSL.create() { implicit b =>
//    import GraphDSL.Implicits._
//
//    val bcast = b.add(Broadcast[Tweet](2))
//    tweets ~> bcast.in
//    bcast.out(0) ~> Flow[Tweet].map(_.auther) ~> writeAuthors
//    bcast.out(1) ~> Flow[Tweet].mapConcat(_.hashtags.toList) ~> writeHashtags
//
//    ClosedShape
//  })
//
//  graph.run()

}
