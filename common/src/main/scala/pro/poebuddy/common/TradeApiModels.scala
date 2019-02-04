package pro.poebuddy.common

import ai.x.play.json.Jsonx
import ai.x.play.json.implicits.optionWithNull
import play.api.libs.json.OFormat


/**
  * https://pathofexile.gamepedia.com/Public_stash_tab_API
  *
  * What a goofy goober of an api, types change all over the place
  * depending on what item it is. Sockets are usually denoted by a string,
  * but for abyssal sockets they are "false"
  */
object TradeApiModels {
  type Category = Map[String, Array[String]]

  implicit val itemPropertyFormat: OFormat[ItemProperty] = Jsonx.formatCaseClassUseDefaults[ItemProperty]
  implicit val socketFormat: OFormat[Socket] = Jsonx.formatCaseClassUseDefaults[Socket]
  implicit val itemFormat: OFormat[Item] = Jsonx.formatCaseClassUseDefaults[Item]
  implicit val stashFormat: OFormat[Stash] = Jsonx.formatCaseClassUseDefaults[Stash]
  implicit val tradeApiUpdateFormat: OFormat[TradeApiUpdate] = Jsonx.formatCaseClassUseDefaults[TradeApiUpdate]

  case class TradeApiUpdate(
                             next_change_id: String,
                             stashes: Seq[Stash] = Seq.empty
                           ) {
    def nextChangeId: String = next_change_id
  }

  case class Stash(
                    accountName: Option[String] = Option.empty,
                    lastCharacterName: Option[String] = Option.empty,
                    id: String,
                    stash: Option[String] = Option.empty,
                    stashType: String,
                    league: String,
                    items: Seq[Item] = Seq.empty,
                    public: Boolean
                  ) {
    def isPublic: Boolean = public
  }

  case class Item(
                   abyssJewel: Boolean = false,
                   additionalProperties: Seq[ItemProperty] = Seq.empty,
                   artFileName: Option[String] = Option.empty,
                   category: Category,
                   corrupted: Boolean = false,
                   colour: Option[String] = Option.empty,
                   cosmeticMods: Seq[String] = Seq.empty,
                   craftedMods: Seq[String] = Seq.empty,
                   descrText: Option[String] = Option.empty,
                   duplicated: Boolean = false,
                   elder: Boolean = false,
                   enchantMods: Seq[String] = Seq.empty,
                   explicitMods: Seq[String] = Seq.empty,
                   flavourText: Seq[String] = Seq.empty, // lmao flavour instead of flavor, silly kiwis
                   frameType: Int,
                   h: Int,
                   icon: String,
                   id: String,
                   identified: Boolean,
                   ilvl: Int,
                   implicitMods: Seq[String] = Seq.empty,
                   inventoryId: Option[String] = Option.empty,
                   relic: Boolean = false,
                   league: String,
                   lockedToCharacter: Boolean = false,
                   maxStackSize: Option[Int] = Option.empty,
                   name: String,
                   nextLevelRequirements: Seq[ItemProperty] = Seq.empty,
                   note: Option[String] = Option.empty,
                   properties: Seq[ItemProperty] = Seq.empty,
                   prophecyDiffText: Option[String] = Option.empty,
                   prophecyText: Option[String] = Option.empty,
                   requirements: Seq[ItemProperty] = Seq.empty,
                   secDescrText: Option[String] = Option.empty,
                   shaper: Boolean = false,
//                   socketedItems: Seq[Item] = Seq.empty, TODO this makes play upset, I wonder what we can do here.
                   socket: Option[Int] = Option.empty,
                   sockets: Seq[Socket] = Seq.empty,
                   stackSize: Option[Int] = Option.empty,
                   support: Boolean = false,
                   talismanTier: Option[Int] = Option.empty,
                   typeLine: String,
                   utilityMods: Seq[String] = Seq.empty,
                   verified: Boolean,
                   w: Int,
                   x: Int,
                   y: Int
                 ) {
    def isAbyssJewel: Boolean = abyssJewel
    def isCorrupted: Boolean = corrupted
    def isDuplicated: Boolean = duplicated
    def isElder: Boolean = elder
    def isRelic: Boolean = relic
    def isLockedToCharacter: Boolean = lockedToCharacter
    def isShaper: Boolean = shaper
    def isSupport: Boolean = support
    def isVerified: Boolean = verified
    def height: Int = h
    def width: Int = w
  }

  case class Socket(
                     group: Int,
                     attr: String,
                     sColour: String
                   )

  case class ItemProperty(
                           name: String,
                           values: Seq[(String, Int)] = Seq.empty, // wtf
                           displayMode: Int,
                           `type`: Option[Int] = Option.empty,
                           progress: Option[BigDecimal] = Option.empty
                         )

}

