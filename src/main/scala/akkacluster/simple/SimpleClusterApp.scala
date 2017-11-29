package akkacluster.simple

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object SimpleClusterApp {

  def startUp(ports: Seq[String]): Unit = {

    ports.foreach { port =>
      // Override the configuration of the port
      val config = ConfigFactory
        .parseString("akka.remote.netty.tcp.port=" + port)
        .withFallback(
          ConfigFactory.load()
        )

      //create an Akka system

      val system = ActorSystem("ClusterSystem",config)
      system.actorOf(Props[SimpleClusterListener],name = "clusterListener")


    }

  }

  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      startUp(Seq("25551", "25552", "0"))
    } else {
      startUp(args)
    }

  }
}
