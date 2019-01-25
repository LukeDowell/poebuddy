package pro.poebuddy.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.twitter.finatra.json.FinatraObjectMapper
import org.scalatest.FlatSpec
import pro.poebuddy.common.TradeApiModels._

import scala.io.Source

class TradeApiModelsTest extends FlatSpec {

  val objectMapper = new FinatraObjectMapper(new ObjectMapper() with ScalaObjectMapper)

  "A TradeApiUpdate" should "deserialize correctly" in {
    val tradeApiUpdateJson = Source.fromResource("trade-api-update.json").mkString
    val tradeApiUpdate = objectMapper.parse[TradeApiUpdate](tradeApiUpdateJson)

    assert(tradeApiUpdate.nextChangeId == "2947-5222-4265-4982-1759")
  }
}
