package com.lightbend.training.scalatrain

import scala.collection.immutable.Seq

case class Train(kind: String, number: Int, schedule: Seq[Station]) {
  require(schedule.take(2).size == 2, s"$schedule must have >= 2 elements")
}
