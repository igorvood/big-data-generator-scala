package ru.vood.bigdata.generator.ent.intf

sealed trait ValueType {


}

object ValueType {

  case object Str extends ValueType

  case object Num extends ValueType

  case object Date extends ValueType

}

