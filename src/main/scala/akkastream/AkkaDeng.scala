package akkastream

import akkastream.SharedRegion.Msg

object SharedRegion {

  type EntryID = String
  type ShardID = String
  type Msg = Any

  type IdExtractor = PartialFunction[Msg, (EntryID, Msg)]
  type ShardResolver = Msg => ShardID

}

sealed trait Command extends Msg with Serializable {
  def sessionID: String
}

case class CreateSession(sessionId: String) extends Command {
  override def sessionID: String = sessionId
}

case




object SharedProcess {

  lazy val idExtractor: SharedRegion.IdExtractor = {
    case cmd: Command => (cmd.sessionID, cmd)
  }

  lazy val shareResolver: SharedRegion.ShardResolver = {
    case cmd: Command => (math.abs(cmd.sessionID.hashCode) % 100).toString
  }

}
