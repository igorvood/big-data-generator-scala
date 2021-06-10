package ru.vood.bigdata.generator.ent.intf

import java.time.LocalDateTime

sealed trait ValueType {

  def defaultGen[T, R](t: T)(implicit mutate: T => R) = mutate

}

object ValueType {

  implicit val defaultStr: AnyVal => String = { q => q.hashCode().toString }

  implicit val defaultNum: AnyVal => Number = { q => q.hashCode() }

  implicit val defaultDate: AnyVal => LocalDateTime = { q =>

    // new LocalDateTime(LocalDate.EPOCH).plusHours(q.hashCode())

    ???

  }


  case object Str extends ValueType {
    override def defaultGen[T, String](t: T)(implicit mutate: T => String) = mutate
  }

  case object Num extends ValueType {
    override def defaultGen[T, String](t: T)(implicit mutate: T => String) = mutate
  }

  case object Date extends ValueType {
    override def defaultGen[T, String](t: T)(implicit mutate: T => String) = mutate
  }

}

