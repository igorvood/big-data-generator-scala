package ru.vood.bigdata.generator.ent.intf

import java.time.LocalDateTime

sealed trait ValueType {
  type OUT_VAL // value type

  //  type IN_VAL
  //  def defaultGen[T](t: T)(implicit mutate: T => OUT_VAL): T => OUT_VAL

  def stringConverter[T](implicit fOutVal: T => OUT_VAL): T => String = { inVal => fOutVal(inVal).toString }

}

object ValueType {

  case object Str extends ValueType {
    override type OUT_VAL = String // value type

    val defaultStr: String => OUT_VAL = { q => q }
    val defaultInt: Int => OUT_VAL = { q => q.toString }

    //    override def defaultGen[T](t: T)(implicit mutate: T => String) = mutate
  }

  case object Num extends ValueType {
    override type OUT_VAL = BigDecimal // value type

    val defaultStr: String => OUT_VAL = { q => q.hashCode() }
    val defaultInt: Int => OUT_VAL = { q => q.hashCode() }

    //    override def defaultGen[T](t: T)(implicit mutate: T => OUT_VAL) = mutate
  }

  case object Date extends ValueType {

    override type OUT_VAL = LocalDateTime // value type

    val defaultString: String => OUT_VAL = _ => LocalDateTime.now()
    val defaultInt: Int => OUT_VAL = _ => LocalDateTime.now()

    //    override def defaultGen[T](t: T)(implicit mutate: T => LocalDateTime) = mutate
  }

}

