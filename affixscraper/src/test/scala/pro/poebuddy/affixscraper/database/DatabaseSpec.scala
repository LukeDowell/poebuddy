package pro.poebuddy.affixscraper.database

import org.scalatest.FlatSpec
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext

class DatabaseSpec extends FlatSpec {

  behavior of "Database"

  val db = Database.forConfig("h2mem1")
  implicit val ec: ExecutionContext = ExecutionContext.global

  it should "be a database" in {

    try {
      class Affixes(tag: Tag) extends Table[(Int, String)](tag, "AFFIXES") {
        def id = column[Int]("AFFIX_ID", O.PrimaryKey)
        def name = column[String]("AFFIX_NAME")

        def * = (id, name)
      }
      val affixes = TableQuery[Affixes]

      val setup = DBIO.seq(
        affixes.schema.create,

        affixes += (1, "Some affix"),

        affixes ++= Seq(
          (2, "asef"),
          (3, "asdfadf")
        )
      )

      val setupFuture = db.run(setup)

    } finally db.close()

  }
}
