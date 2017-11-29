package akkacluster.simple

import akka.actor.{Actor, ActorLogging}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent._

class SimpleClusterListener extends Actor with ActorLogging {

  private val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self,
                      initialStateMode = InitialStateAsEvents,
                      classOf[MemberEvent],
                      classOf[UnreachableMember])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def receive = {
    case MemberUp(member) => log.info("member is up:{}", member.address)
    case UnreachableMember(member) =>
      log.info("member detected as unreachable:{}", member)

    case MemberRemoved(member, previousStatus) =>
      log.info("member is removed:{} after {}", member.address, previousStatus)

    case _: MemberEvent => //ignore

  }
}
