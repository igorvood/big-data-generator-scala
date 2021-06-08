package ru.vood.bigdata.generator.ent

import ru.vood.bigdata.generator.ent.intf.{TypeColumn, ValueType}

case class Column(
                   val name: String,
                   val valueType: ValueType,
                   val typeColumn: TypeColumn
                 )
