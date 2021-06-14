package ru.vood.bigdata.generator.ent.clu

import ru.vood.bigdata.generator.ent.intf.EntityFun
import ru.vood.bigdata.generator.ent.score.{DataCreator, Score}

case class Clu(id: String,
          score: Score,
          overridenCollsList: List[((String, Score)) => String]


         ) extends DataCreator {

  override def csvStr(implicit meta: Map[String, EntityFun]): String = overridenCollsList
    .map { q => q(id, score) }
    .mkString(";")

}
