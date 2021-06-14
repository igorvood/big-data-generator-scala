package ru.vood.bigdata.generator

import ru.vood.bigdata.generator.ent.clu.Clu
import ru.vood.bigdata.generator.ent.intf.ValueType.{Date, Num, Str}
import ru.vood.bigdata.generator.ent.intf.{EntityFun, MetaDelete}
import ru.vood.bigdata.generator.ent.score.Score

import java.time.LocalDateTime

object Main {

  implicit val totalMeta: Map[String, EntityFun] = MetaDelete.getMeta

  def main(args: Array[String]): Unit = {
    val overrideScore = Map[String, String => String](("mer_sign", { q => (Math.abs(q.hashCode) % 2).toString }), ("date_cr", { q => LocalDateTime.now().plusMinutes(q.hashCode).toString }))
    val score = "score"
    val list = scoreFuns(totalMeta, score, overrideScore).map { q => q._3 }
      .toList

    val scoreData: Set[Score] = (1 to 2000000)
      .map { id =>
        Score(id.toString, /*overrideScore, */ list, { _ => Set[Clu]() })
      }
      .toSet


    import java.io.BufferedWriter
    import java.io.FileWriter
    val writer: BufferedWriter = new BufferedWriter(new FileWriter("e:/temp/"+score+".csv"))
    scoreData.foreach(q => writer.write(q.csvStr+"\n") )

    writer.close()

//    val value = scoreData.map { q => q.csvStr }.mkString("\n")
//
//    println(value)

  }

  private def scoreFuns(meta: Map[String, EntityFun], nameEnt: String, overrideDefaults: Map[String, String => String]) = {
    val value1 = meta(nameEnt).cols.map { defFun =>
      val function = defFun.valueType match {
        case Str => Str.stringConverter(Str.defaultStr)
        case Num => Num.stringConverter(Num.defaultNum)
        case Date => Date.stringConverter(Date.defaultDate)
        case _ => throw new IllegalStateException("asd")
      }
      val overrid = overrideDefaults.getOrElse(defFun.name, function)
      (defFun.name, defFun, overrid)
    }
    value1
  }


}

