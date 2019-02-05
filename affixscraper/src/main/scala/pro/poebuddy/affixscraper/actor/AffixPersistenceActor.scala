package pro.poebuddy.affixscraper.actor

import akka.actor.{Actor, ActorLogging, Props}
import pro.poebuddy.common.AffixModels.Affix

class AffixPersistenceActor extends Actor with ActorLogging {
  override def receive: Receive = ???
}

object AffixPersistenceActor {
  def props: Props = Props[AffixPersistenceActor]

  case class WriteAffixes(affixes: Seq[Affix])
  case class WriteSuccess()
  case class WriteFailed(err: String)
}