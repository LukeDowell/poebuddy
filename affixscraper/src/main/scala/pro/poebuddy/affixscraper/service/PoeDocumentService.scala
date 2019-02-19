package pro.poebuddy.affixscraper.service

import org.jsoup.nodes.{Document, Element}
import pro.poebuddy.affixscraper.extensions.JsoupExtensions._
import pro.poebuddy.common.AffixModels.PoeAffix


object PoeDocumentService {

  /**
    * Poedb has a general mod page that lists every mod in the game in an unorganized way which isn't useful to us.
    * This page also has a panel that contains links to every item type and their relevant mods. This function
    * extracts the name of the item type and the link to that type's mod page. The item names are not formatted
    * in a consistent way, this will have to be dealt with later. As an example, one item type may be named
    * "One Hand Swords" and another might be "top_tier_map" while y mod page
    */
  def extractItemModPages(doc: Document): Map[String, String] = {
    def elementToEquipmentAndUrlPair(el: Element): (String, String) = {
      (el.text(), el.selectFirst("a").attr("abs:href"))
    }

    doc.select("#navbar-collapse2 > .navbar-nav > .dropdown > .dropdown-menu > li")
      .map(elementToEquipmentAndUrlPair)
      .toMap
  }

  /**
    * This attempts to extract specific affix information from a given item type page. There are several problems
    * to consider with these pages.
    *.
    *  - The initial prefix / suffix panels contain essence affixes and crafting affixes mixed in with the normal
    *    affixes
    *  - The shaper and elder sections do not indicate whether they are prefix or suffix
    *  - There are two shaper and elder sections
    *  - Vaal modifications need to be selected independently as they don't follow the same format of the other
    *    modifications
    *  - Delve mods don't have percentages associated with them
    *
    * @param itemType A string representing the poedb item type, like "Claws" or "One Hand Swords"
    * @param doc A jsoup document for a given item page on poedb
    * @return
    */
  def extractItemTypeAffixes(itemType: String, doc: Document): Seq[PoeAffix] = {
    val modCategoryPanels = doc.select(".col-lg-6")

    def findTableParserForTitle(title: String): TableModParser = Seq(EssenceModParser, DelveModParser)
      .find(_.targetModRegex.findFirstMatchIn(title).isDefined)
      .getOrElse(StandardModParser)

    modCategoryPanels.flatMap(modPanel => {
      val panelChildren = modPanel.children()
      val panelTitle = panelChildren.head.text()
      val tableModParser = findTableParserForTitle(panelTitle)

      panelChildren.tail
        .grouped(2)
        .map(pair => (pair.head, pair.tail.head))
        .map(new ModParser(_, panelTitle).parse(tableModParser))
        .toSeq
    })
  }
}
