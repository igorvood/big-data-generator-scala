package ru.vood.bigdata.generator.ent.intf

import java.sql.Date
import java.time.{LocalDate, LocalDateTime}

sealed trait ValueType[R] {

  implicit val defaultStr: AnyVal => String = { q => q.hashCode().toString }

  implicit val defaultNum: AnyVal => Number = { q => q.hashCode() }

  implicit val defaultDate: AnyVal => LocalDateTime = { q =>

    new LocalDateTime(LocalDate.EPOCH).plusHours(q.hashCode())

  }

  def defaultGen(t: AnyVal)(implicit mutate: AnyVal => R): R

}

object ValueType {


  case object Str extends ValueType[String] {
    override def defaultGen(t: AnyVal)(implicit mutate: AnyVal => String): String = mutate(t)
  }

  case object Num extends ValueType[Number] {
    override def defaultGen(t: AnyVal)(implicit mutate: AnyVal => Number): Number = mutate(t)
  }

  case object Date extends ValueType[LocalDateTime] {
    override def defaultGen(t: AnyVal)(implicit mutate: AnyVal => LocalDateTime): LocalDateTime = mutate(t)
  }

}

