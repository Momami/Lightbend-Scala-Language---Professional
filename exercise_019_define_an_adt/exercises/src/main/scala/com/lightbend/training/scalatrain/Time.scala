package com.lightbend.training.scalatrain

case class Time(hours: Int = 0, minutes: Int = 0) {
  val asMinutes: Int = hours * 60 + minutes

  def minus(that: Time): Int = asMinutes - that.asMinutes
  def -(that: Time): Int = minus(that)

  override def toString: String = f"$hours%02d:$minutes%02d"

  require(hours >= 0 && hours <= 23,
    s"$hours should be between 0and 23, inclusive")
  require(minutes >= 0 && minutes <= 59,
    s"$minutes should be between 0and 23, inclusive")
}

object Time {
  def fromMinutes(minutes: Int): Time = Time(minutes / 60, minutes % 60)
}
