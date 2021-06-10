package ru.vood.bigdata.generator.ent.intf

import java.time.LocalDateTime

sealed trait ValueType {
  type OUT_VAL // value type

  //  type IN_VAL
  def defaultGen[T](t: T)(implicit mutate: T => OUT_VAL): T => OUT_VAL

  def stringConverter[T](implicit fOutVal: T => OUT_VAL): T => String = { inVal => fOutVal(inVal).toString }

}

object ValueType {

  case object Str extends ValueType {
    override type OUT_VAL = String // value type

    implicit val defaultStr: String => OUT_VAL = { q => q }

    override def defaultGen[T](t: T)(implicit mutate: T => String) = mutate
  }

  case object Num extends ValueType {
    override type OUT_VAL = BigDecimal // value type

    implicit val defaultNum: String => OUT_VAL = { q => q.hashCode() }

    override def defaultGen[T](t: T)(implicit mutate: T => BigDecimal) = mutate
  }

  case object Date extends ValueType {

    override type OUT_VAL = LocalDateTime // value type

    implicit val defaultDate: String => OUT_VAL = _ => LocalDateTime.now()

    override def defaultGen[T](t: T)(implicit mutate: T => LocalDateTime) = mutate
  }

}

