package akkacluster.simple

import akka.actor.{Actor, ActorLogging}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.{
  CurrentClusterState,
  MemberEvent,
  UnreachableMember
}

class SimpleClusterListener2 extends Actor with ActorLogging {

  val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberEvent], classOf[UnreachableMember])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def receive = {
    case state: CurrentClusterState =>
      log.info("current members:{}", state.members.mkString(","))
    case _: MemberEvent => //ignore
  }
}
