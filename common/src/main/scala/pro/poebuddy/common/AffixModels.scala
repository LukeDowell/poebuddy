package pro.poebuddy.common

import ai.x.play.json.Jsonx
import play.api.libs.json.OFormat

object AffixModels {

  implicit val affixFormat: OFormat[Affix] = Jsonx.formatCaseClass[Affix]

  /**
    * First attempt at representing an affix
    * @param name The name of an affix. ex: Frosted
    * @param tier The tier of an affix. ex: 1
    * @param effect The effect of an affix. ex: Adds x to x cold damage.
    * @param values The values of an affix. ex: [(1-2), (3)]
    * @param source The source of an affix. ex: Prefix, or Delve, or Essence
    * @param fossilTags Relevant tags for this affix, if any, ex: ("Attack", "Cold")
    * @param requiredItemLevel The required item level for an affix to appear. ex: 2
    * @param isLocal Whether or not the affix is local. If false, it's global.
    * @param chance The chance for an affix to appear. This is probably only relevant for crafting with
    *               alteration / augmentation / transmutation / chaos / orbs. GGG hasn't released any sort of stats
    *               for crafting with fossils or essences.
    */
  case class Affix(
                    name: String,
                    tier: String,
                    effect: String,
                    values: Seq[Array[Int]] = Seq.empty,
                    source: String,
                    fossilTags: Seq[String] = Seq.empty,
                    requiredItemLevel: Int,
                    isLocal: Boolean,
                    chance: Double
                  )

  case class PoeAffix(
                       effect: String,
                       fossilTags: Seq[String] = Seq.empty,
                       source: String,
                       tiers: Seq[TieredPoeAffix] = Seq.empty,
                       isLocal: Boolean
                     )

  case class TieredPoeAffix(
                             name: String,
                             tier: Int,
                             values: Seq[Array[Int]] = Seq.empty,
                             requiredItemLevel: Int,
                             chance: Double = 0.0
                           )
}
