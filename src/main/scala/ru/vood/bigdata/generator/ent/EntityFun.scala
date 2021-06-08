package ru.vood.bigdata.generator.ent

trait EntityFun[E] {

  def nameEntity: String

  def cols: Set[Column]

}
