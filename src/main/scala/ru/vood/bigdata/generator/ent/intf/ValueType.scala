package ru.vood.bigdata.generator.ent.intf

import java.time.LocalDateTime

sealed trait ValueType {
  type V // value type

  def defaultGen[T](t: T)(implicit mutate: T => V): T => V

}

object ValueType {

  case object Str extends ValueType {
    override type V = String // value type

    implicit val defaultStr: String => V = { q => q }

    override def defaultGen[T](t: T)(implicit mutate: T => String) = mutate
  }

  case object Num extends ValueType {
    override type V = BigDecimal // value type

    implicit val defaultNum: String => V = { q => q.hashCode() }

    override def defaultGen[T](t: T)(implicit mutate: T => BigDecimal) = mutate
  }

  case object Date extends ValueType {

    override type V = LocalDateTime // value type

    implicit val defaultDate: String => V = _ => LocalDateTime.now()

    override def defaultGen[T](t: T)(implicit mutate: T => LocalDateTime) = mutate
  }

}

