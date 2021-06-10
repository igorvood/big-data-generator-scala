package ru.vood.bigdata.generator.ent

import ru.vood.bigdata.generator.ent.intf.{TypeColumn, ValueType}

case class Column(name: String,
                  valueType: ValueType,
                  typeColumn: TypeColumn
                 )
