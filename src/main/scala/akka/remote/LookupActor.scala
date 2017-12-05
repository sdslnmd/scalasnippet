//package akka.remote
//import scala.concurrent.duration._
//import akka.actor.{Actor, ActorIdentity, ReceiveTimeout}
//import akka.remote.ContainerFormats.ActorIdentity
//
//class LookupActor(path: String) extends Actor {
//
//  sendIdentifyRequest()
//
//  def sendIdentifyRequest(): Unit = {
////    context.actorSelection(path) ! identity(path)
////    import context.dispatcher
////    context.system.scheduler.scheduleOnce(3.second, self, ReceiveTimeout)
//  }
//
//  def identifying:Actor.Receive={
////    case ActorIdentity('')
//
//  }
//
//  override def receive = ???
//}
