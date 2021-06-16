package ru.vood.bigdata.generator.ent.score

import ru.vood.bigdata.generator.ent.Column
import ru.vood.bigdata.generator.ent.clu.Clu
import ru.vood.bigdata.generator.ent.intf.{EntityFun, MetaConverter}

import java.time.LocalDateTime

case class Score(
                  id: Int,
                  scoreFuns: Vector[Int => String],
                  colsMeta: Map[String, (Column, Int)],
                  cluCnt: Int,
                  cluFuns: List[((String, Score)) => String]
                ) extends MetaConverter with DataCreator {

  val entName = "score"

  lazy val merSign = scoreFuns(colsMeta("mer_sign")._2)(id)
  require(merSign == "0" || merSign == "1"
    , {
      s"$entName.mer_sign must be 0 or 1, actual is $merSign "
    }
  )


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
      Clu(n.toString, this, cluFuns)
    }.toSet

  }

}
