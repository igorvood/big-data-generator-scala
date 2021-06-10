package ru.vood.bigdata.generator.ent

import ru.vood.bigdata.generator.ent.intf.EntityFun

trait GenEntity[E] {

  type E // type of an element

//  def genValue(e: E, field: String)(implicit funs: EntityFun)
}
