package pro.poebuddy.escuchador.actor

import akka.actor.{Actor, ActorLogging, Props}

class TradeApiActor extends Actor with ActorLogging {
  override def receive: Receive = ???
}

object TradeApiActor {
  def props: Props = Props[TradeApiActor]
}