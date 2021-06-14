package ru.vood.bigdata.generator

import ru.vood.bigdata.generator.ent.clu.Clu
import ru.vood.bigdata.generator.ent.intf.ValueType.{Date, Num, Str}
import ru.vood.bigdata.generator.ent.intf.{EntityFun, MetaDelete}
import ru.vood.bigdata.generator.ent.score.Score

import java.time.LocalDateTime

object Main {

  implicit val totalMeta: Map[String, EntityFun] = MetaDelete.getMeta

  def main(args: Array[String]): Unit = {
    val overrideScore = Map[String, String => String](("mer_sign", { q => q.hashCode.toString }), ("date_cr", { q => LocalDateTime.now().plusMinutes(q.hashCode).toString }))
    val meta = totalMeta
    val score = "score"

    val scoreMeta = meta(score).cols
    val value1 = scoreMeta.map { defFun =>

      val function = defFun.valueType match {
        case Str => Str.stringConverter(Str.defaultStr)
        case Num => Num.stringConverter(Num.defaultNum)
        case Date => Date.stringConverter(Date.defaultDate)
        case _ => throw new IllegalStateException("asd")
      }
      val overrid = overrideScore.getOrElse(defFun.name, function)
      (defFun.name, defFun, overrid)
    }
    val list = value1
      .map { q => q._3 }
      .toList


    val scoreData = (1 to 20)
      .map { id =>
        Score(id.toString, /*overrideScore, */ list, { _ => Set[Clu]() })
      }
      .toSet

    val value = scoreData.map { q => q.csvStr }.mkString("\n")

    println(value)

  }

}

