package pro.poebuddy.affixscraper.service

import org.jsoup.Jsoup
import org.scalatest.FlatSpec

import scala.io.Source

class PoeDocumentServiceTest extends FlatSpec {

  "A PoeDocumentService" should "extract accurate urls for each equipment type" in {
    val html = Source.fromResource("poedb_main/poedb_main.html").mkString
    val doc = Jsoup.parse(html)

    val map = PoeDocumentService.extractItemModPages(doc)

    assert(map.nonEmpty)
    assert(map("Gloves(Str)") == "http://poedb.tw/us/mod.php?cn=Gloves&an=str_armour")
  }

  it should "extract the correct claw affixes" in {
    val html = Source.fromResource("poedb_claw/poedb_claw.html").mkString
    val doc = Jsoup.parse(html)

    val map = PoeDocumentService.extractItemTypeAffixes("Claws", doc)

    assert(map.nonEmpty)
  }
}
