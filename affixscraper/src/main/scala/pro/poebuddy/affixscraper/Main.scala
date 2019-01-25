package pro.poebuddy.affixscraper

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import pro.poebuddy.affixscraper.actor.ItemUrlExtractorActor

import scala.concurrent.ExecutionContext

object Main extends App with LazyLogging {

  logger.info("Starting affix scraper...")

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = ExecutionContext.global

  actorSystem.actorOf(ItemUrlExtractorActor.props)
}
