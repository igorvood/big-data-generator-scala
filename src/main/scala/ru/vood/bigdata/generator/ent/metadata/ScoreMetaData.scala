package ru.vood.bigdata.generator.ent.metadata

import ru.vood.bigdata.generator.ent.Column
import ru.vood.bigdata.generator.ent.intf.EntityFun
import ru.vood.bigdata.generator.ent.intf.ValueType.{Date, Num, Str}

import java.time.LocalDateTime

object ScoreMetaData {

  lazy val scoreFunsData: Set[(String, Column, Int => String)] = scoreFuns(overrideScore)

  lazy val sFunsListData = scoreFuns(overrideScore).map { q => q._3 }.toList

  val overrideScore = Map[String, Int => String](
    ("mer_sign", { q => (Math.abs(q.hashCode) % 2).toString }),
    ("date_cr", { q => LocalDateTime.now().plusMinutes(q.hashCode).toString })
  )

  val score = "score"

  private val scoreMeta: EntityFun = MetaDelete.getMeta(score)

  private def scoreFuns(overrideDefaults: Map[String, Int => String]): Set[(String, Column, Int => String)] = {
    val value1: Set[(String, Column, Int => String)] = scoreMeta.cols.map { defFun =>
      val function = defFun.valueType match {
        case Str => Str.stringConverter(Str.defaultInt)
        case Num => Num.stringConverter(Num.defaultInt)
        case Date => Date.stringConverter(Date.defaultInt)
        case _ => throw new IllegalStateException("asd")
      }
      val overrid = overrideDefaults.getOrElse(defFun.name, function)
      (defFun.name, defFun, overrid)
    }
    value1
  }


}
