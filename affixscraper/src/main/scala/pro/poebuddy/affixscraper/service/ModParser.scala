package pro.poebuddy.affixscraper.service

import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import pro.poebuddy.common.AffixModels.{PoeAffix, TieredPoeAffix}
import pro.poebuddy.affixscraper.extensions.JsoupExtensions._

import scala.util.matching.Regex

class ModParser(affixPair: (Element, Element), source: String) {

  val affixInfo: Element = affixPair._1
  val affixTable: Elements = affixPair._2.select("table")
  val fossilTags: Array[String] = affixInfo.children().first().text().split(" ").distinct

  affixInfo.children().first().remove() // So the tags don't pollute our effect text

  val effectText: String = affixInfo.text()
  val isLocal: Boolean = affixTable.select("tr > th").exists(_.text().equalsIgnoreCase("Local"))

  def parse(tableModParser: TableModParser): PoeAffix = PoeAffix(
    effect = effectText,
    fossilTags = fossilTags,
    source = "",
    tiers = tableModParser.parseTable(affixTable),
    isLocal = isLocal
  )
}

trait TableModParser {
  val targetModRegex: Regex
  def parseTable(table: Elements): Seq[TieredPoeAffix]
}

object StandardModParser extends TableModParser {
  override val targetModRegex: Regex = raw"(Prefix|Suffix)".r

  override def parseTable(table: Elements): Seq[TieredPoeAffix] = {

    Seq.empty
  }
}

object EssenceModParser extends TableModParser {
  override val targetModRegex: Regex = raw"Essence.(Prefix|Suffix)".r

  override def parseTable(table: Elements): Seq[TieredPoeAffix] = {

    Seq.empty
  }
}

object DelveModParser extends TableModParser {
  override val targetModRegex: Regex = raw"Delve.(Prefix|Suffix)".r

  override def parseTable(table: Elements): Seq[TieredPoeAffix] = {

    Seq.empty
  }
}
