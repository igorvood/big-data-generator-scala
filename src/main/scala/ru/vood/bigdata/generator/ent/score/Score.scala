package ru.vood.bigdata.generator.ent.score

import ru.vood.bigdata.generator.ent.Column
import ru.vood.bigdata.generator.ent.clu.Clu
import ru.vood.bigdata.generator.ent.intf.{EntityFun, MetaConverter}

import java.time.LocalDateTime

case class Score(
                  id: String,
                  overridenColls: Map[String, String => String],
                  clus: String => Set[Clu]
                ) extends MetaConverter with DataCreator {

  val entName = "score"

  implicit val str: String => String = it => it
  implicit val num: String => BigDecimal = it => it.hashCode
  implicit val date: String => LocalDateTime = _ => LocalDateTime.now()


  override def csvStr(implicit meta: Map[String, EntityFun]): String = {
    val defaultFun: Set[Column] = meta(entName).cols
    val value: List[(String, Column, String => String)] = defaultFun
      .map { defFun =>
        val overrid: String => String = overridenColls.getOrElse(defFun.name, defFun.valueType.defaultGen(id))
        (defFun.name, defFun, overrid)

//        val tuple: (String, Column, String => String) =
//          if (overrid == null) (defFun.name, defFun, defFun.valueType.defaultGen)
//          else (defFun.name, defFun, defFun.valueType.defaultGen(overrid))
//        tuple
      }.toList
    val value1 = value
      .map(cols => {
        val str2 = cols._3(id)
        str2
      }).toList
    val str1 = value1
      .mkString(";")
    return str1
  }


}
