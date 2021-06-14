package ru.vood.bigdata.generator.ent.score

import ru.vood.bigdata.generator.ent.clu.Clu
import ru.vood.bigdata.generator.ent.intf.{EntityFun, MetaConverter}

import java.time.LocalDateTime

case class Score(
                  id: Int,
                  //                  overridenColls: Map[String, String => String],
                  scoreFuns: List[Int => String],
                  cluCnt: Int,
                  cliFuns: List[((String, Score)) => String]


                  //                  clus: String => Set[Clu]
                ) extends MetaConverter with DataCreator {

  val entName = "score"

  implicit val str: String => String = it => it
  implicit val num: String => BigDecimal = it => it.hashCode
  implicit val date: String => LocalDateTime = _ => LocalDateTime.now()

  override def csvStr(implicit meta: Map[String, EntityFun]): String = {
    scoreFuns
      .map { q => q(id) }
      .mkString(";")
  }


  def clus(): Set[Clu] = {
    (1 to cluCnt).map { n =>
      Clu(n.toString, this, cliFuns)
    }.toSet

  }

}
