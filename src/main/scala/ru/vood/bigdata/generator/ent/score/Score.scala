package ru.vood.bigdata.generator.ent.score

import ru.vood.bigdata.generator.ent.clu.Clu
import ru.vood.bigdata.generator.ent.intf.{EntityFun, MetaConverter}

import java.time.LocalDateTime

case class Score(
                  id: String,
                  //                  overridenColls: Map[String, String => String],
                  overridenCollsList: List[String => String],
                  cluCnt: Int
//                  clus: String => Set[Clu]
                ) extends MetaConverter with DataCreator {

  val entName = "score"

  implicit val str: String => String = it => it
  implicit val num: String => BigDecimal = it => it.hashCode
  implicit val date: String => LocalDateTime = _ => LocalDateTime.now()


  override def csvStr(implicit meta: Map[String, EntityFun]): String = {
    overridenCollsList
      .map { q => q(id) }
      .mkString(";")
  }


  def clus() : Set[Clu]={
    (1 to cluCnt).map{ n=>
      Clu(n.toString, this, List())
    }.toSet

  }

}
