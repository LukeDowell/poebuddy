package pro.poebuddy.common

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import pro.poebuddy.common.TradeApiModels.Category

/**
  * https://pathofexile.gamepedia.com/Public_stash_tab_API
  *
  * What a goofy goober of an api, types change all over the place
  * depending on what item it is. Sockets are usually denoted by a string,
  * but for abyssal sockets they are "false"
  */
object TradeApiModels {
  type Category = Map[String, Array[String]]
}

case class TradeApiUpdate(
                           @JsonProperty("next_change_id") nextChangeId: String,
                           @JsonProperty("stashes") stashes: Seq[Stash] = Seq.empty
                         )

@JsonCreator
case class Stash(
                  accountName: Option[String] = Option.empty,
                  lastCharacterName: Option[String] = Option.empty,
                  id: String,
                  stash: Option[String] = Option.empty,
                  stashType: String,
                  league: String,
                  items: Seq[Item] = Seq.empty,
                  @JsonProperty("public") isPublic: Boolean
                )

@JsonCreator
case class Item(
                 @JsonProperty("abyssJewel") isAbyssJewel: Boolean = false,
                 additionalProperties: Seq[ItemProperty] = Seq.empty,
                 artFileName: Option[String] = Option.empty,
                 category: Category,
                 @JsonProperty("corrupted") isCorrupted: Boolean = false,
                 @JsonProperty("colour") color: Option[String] = Option.empty,
                 cosmeticMods: Seq[String] = Seq.empty,
                 craftedMods: Seq[String] = Seq.empty,
                 descrText: Option[String] = Option.empty,
                 @JsonProperty("duplicated") isDuplicated: Boolean = false,
                 @JsonProperty("elder") isElder: Boolean = false,
                 enchantMods: Seq[String] = Seq.empty,
                 explicitMods: Seq[String] = Seq.empty,
                 @JsonProperty("flavourText") flavorText: Seq[String] = Seq.empty, // lmao flavour instead of flavor, silly kiwis
                 frameType: Int,
                 @JsonProperty("h") height: Int,
                 icon: String,
                 id: String,
                 @JsonProperty("identified") isIdentified: Boolean,
                 @JsonProperty("ilvl") itemLevel: Int,
                 implicitMods: Seq[String] = Seq.empty,
                 inventoryId: Option[String] = Option.empty,
                 @JsonProperty("relic") isRelic: Boolean = false,
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
                 @JsonProperty("shaper") isShaper: Boolean = false,
                 socketedItems: Seq[Item] = Seq.empty,
                 socket: Option[Int] = Option.empty,
                 sockets: Seq[Socket] = Seq.empty,
                 stackSize: Option[Int] = Option.empty,
                 @JsonProperty("support") isSupport: Boolean = false,
                 talismanTier: Option[Int] = Option.empty,
                 typeLine: String,
                 utilityMods: Seq[String] = Seq.empty,
                 @JsonProperty("verified") isVerified: Boolean,
                 @JsonProperty("w") width: Int,
                 x: Int,
                 y: Int
               )

@JsonCreator
case class Socket(
                   group: Int,
                   attr: String,
                   @JsonProperty("sColour") color: String
                 )

@JsonCreator
case class ItemProperty(
                         name: String,
                         values: Array[Array[String]], // wtf
                         displayMode: Int,
                         `type`: Option[Int] = Option.empty,
                         progress: Option[Int] = Option.empty
                       )
