package pro.poebuddy.common

import org.scalatest.FlatSpec
import play.api.libs.json.{JsResult, Json}
import pro.poebuddy.common.TradeApiModels._

import scala.io.Source

class TradeApiModelsTest extends FlatSpec {

  "A TradeApiUpdate" should "deserialize correctly" in {
    def deserialize(str: String): JsResult[TradeApiUpdate] = {
      Json.parse(str).validate[TradeApiUpdate]
    }

    val tradeApiUpdateJson = Source.fromResource("trade-api-update.json").mkString
    val tradeApiUpdate = deserialize(tradeApiUpdateJson)

    assert(tradeApiUpdate.get.next_change_id == "2947-5222-4265-4982-1759")
  }
}
