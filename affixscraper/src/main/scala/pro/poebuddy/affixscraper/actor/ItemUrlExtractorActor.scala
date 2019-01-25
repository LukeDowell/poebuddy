package pro.poebuddy.affixscraper.actor

import java.net.URL

import akka.actor.{Actor, ActorLogging, Props}
import com.typesafe.config.ConfigFactory
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import pro.poebuddy.affixscraper.service.PoeDocumentService

import scala.concurrent.{ExecutionContext, Future}

class ItemUrlExtractorActor extends Actor with ActorLogging {
  import ItemUrlExtractorActor._

  implicit val ec: ExecutionContext = context.dispatcher

  override def preStart(): Unit = {
    val targetUrl = ConfigFactory.load().getString("poedb.mods.url")
    log.info(s"Pulling doc from $targetUrl")
    self ! DocumentMessage(Jsoup.parse(new URL(targetUrl), JsoupTimeoutMillis))
  }

  override def receive: Receive = {
    case DocumentMessage(doc) =>
      log.info(s"Received document with title=${doc.title()}, processing...")
      PoeDocumentService.extractItemModPages(doc)
        .mapValues(new URL(_))
        .map { case (itemType, url) =>
          Future {
            context.actorOf(ItemAffixActor.props) ! ItemModDocument(itemType, Jsoup.parse(url, JsoupTimeoutMillis))
          }
      }

    case unknown => log.error(s"Unknown message $unknown")
  }
}

object ItemUrlExtractorActor {
  def props: Props = Props[ItemUrlExtractorActor]
  val JsoupTimeoutMillis = 5000

  case class DocumentMessage(doc: Document)
  case class ItemModDocument(itemType: String, doc: Document)
}
