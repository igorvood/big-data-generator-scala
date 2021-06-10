package ru.vood.bigdata.generator.ent.score

import ru.vood.bigdata.generator.ent.intf.EntityFun

trait DataCreator {

  def csvStr(implicit meta: Map[String, EntityFun]): String

}
