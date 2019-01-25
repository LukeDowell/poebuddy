package pro.poebuddy.affixscraper.actor

import akka.actor.{Actor, ActorLogging, Props}
import pro.poebuddy.affixscraper.actor.ItemUrlExtractorActor.ItemModDocument
import pro.poebuddy.affixscraper.service.PoeDocumentService

class ItemAffixActor extends Actor with ActorLogging {

  override def receive: Receive = {
    case ItemModDocument(itemType, doc) =>
      log.info(s"Received request to process document for itemType=$itemType")
      val mods = PoeDocumentService.extractItemTypeAffixes(itemType, doc)
      mods.foreach(println)
  }
}

object ItemAffixActor {
  def props: Props = Props[ItemAffixActor]
}
