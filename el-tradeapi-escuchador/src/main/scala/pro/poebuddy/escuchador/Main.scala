package pro.poebuddy.escuchador

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging

object Main extends App with LazyLogging {
  logger.info("Estoy empezando a escuchar, mi amigo")

  implicit val actorSystem: ActorSystem = ActorSystem()
}
