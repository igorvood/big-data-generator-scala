package ru.vood.bigdata.generator.ent

trait GenEntity[E] {

  type E // type of an element

  //  def genValue(e: E, field: String)(implicit funs: EntityFun)
}
