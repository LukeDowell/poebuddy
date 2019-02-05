package pro.poebuddy.affixscraper.extensions

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import scala.collection.JavaConverters._

object JsoupExtensions {

  implicit def elementsToScalaList(elements: Elements): List[Element] = elements.asScala.toList

}
