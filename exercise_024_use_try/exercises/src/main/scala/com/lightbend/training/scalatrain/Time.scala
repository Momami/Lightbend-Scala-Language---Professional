package com.lightbend.training.scalatrain

import play.api.libs.json.{JsNumber, JsValue, Json}

import scala.util.Try

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  val asMinutes: Int = hours * 60 + minutes

  def minus(that: Time): Int = asMinutes - that.asMinutes

  def -(that: Time): Int = minus(that)
  override def toString: String = f"$hours%02d:$minutes%02d"

  require(hours >= 0 && hours <= 23,
    s"$hours should be between 0and 23, inclusive")
  require(minutes >= 0 && minutes <= 59,
    s"$minutes should be between 0and 23, inclusive")

  override def compare(that: Time): Int = asMinutes - that.asMinutes
  def toJson: JsValue = {
    Json.obj(
      "hours" -> JsNumber(hours),
      "minutes" -> JsNumber(minutes))
  }
}

object Time {
  def fromMinutes(minutes: Int): Time = Time(minutes / 60, minutes % 60)

  def fromJson(jsValue: JsValue): Option[Time] = {
    for {
      h <- Try((jsValue \ "hours").as[Int])
      m <- Try((jsValue \ "minutes").as[Int]).recover {case _: Exception => 0}
    } yield Time(h, m)
  }.toOption
}
