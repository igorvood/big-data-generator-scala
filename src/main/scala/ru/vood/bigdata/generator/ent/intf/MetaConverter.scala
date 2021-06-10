package ru.vood.bigdata.generator.ent.intf

import ru.vood.bigdata.generator.ent.Column

trait MetaConverter {

  def column(implicit col: Set[Column]) = col.map { c => (c.name, col) }.toMap


}
