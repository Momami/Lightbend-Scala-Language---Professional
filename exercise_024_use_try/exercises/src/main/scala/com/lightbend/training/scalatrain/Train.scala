package com.lightbend.training.scalatrain

import scala.collection.immutable.Seq

case class Train(info: TrainInfo,
                 schedule: Seq[(Time, Station)]) {
  require(schedule.take(2).size == 2, s"$schedule must have >= 2 elements")
  val stations: Seq[Station] = schedule.map(_._2)
  def timeAt(station: Station): Option[Time] =
    schedule.find{
      case (_, s) => s == station
    }.map{
      case (time, _) => time
    }
}

sealed abstract class TrainInfo {
  def number: Int
}

case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo
case class RegionalExpress(number: Int) extends TrainInfo
case class BavarianRegional(number: Int) extends TrainInfo
