package ru.vood.bigdata.generator

import ru.vood.bigdata.generator.ent.Column
import ru.vood.bigdata.generator.ent.intf.EntityFun
import ru.vood.bigdata.generator.ent.intf.ValueType.{Date, Num, Str}
import ru.vood.bigdata.generator.ent.metadata.MetaDelete
import ru.vood.bigdata.generator.ent.metadata.ScoreMetaData.{colsData, sFunsListData}
import ru.vood.bigdata.generator.ent.score.Score

import java.io.{BufferedWriter, FileWriter}
import java.time.LocalDateTime

object Main {

  implicit val totalMeta: Map[String, EntityFun] = MetaDelete.getMeta

  def main(args: Array[String]): Unit = {

    val score = "score"
    val cluFunsData = cluFuns(totalMeta, "clu", Map()).map { q => q._3 }.toList


    val scoreData: Set[Score] = (1 to 20)
      .map { id =>
        Score(id, sFunsListData, colsData, 100, cluFunsData)
      }
      .toSet

    println("begin " + LocalDateTime.now())
    val writerScore: BufferedWriter = new BufferedWriter(new FileWriter("e:/temp/" + score + ".csv"))
    scoreData.foreach(q => writerScore.write(q.csvStr + "\n"))
    writerScore.close()

    println("write Score " + LocalDateTime.now())

    val writerClu: BufferedWriter = new BufferedWriter(new FileWriter("e:/temp/clu.csv"))
    scoreData
      .flatMap(s => s.clus())
      .foreach(q => writerClu.write(q.csvStr + "\n"))
    writerClu.close()
    println("write clu" + LocalDateTime.now())

    //    запись скоров окончена
  }


  private def cluFuns(meta: Map[String, EntityFun], nameEnt: String, overrideDefaults: Map[String, ((String, Score)) => String]): Set[(String, Column, ((String, Score)) => String)] = {
    val value1: Set[(String, Column, ((String, Score)) => String)] = meta(nameEnt).cols.map { defFun =>
      val function: ((String, Score)) => String = defFun.valueType match {
        case Str => Str.stringConverter[(String, Score)](id => s"${id._2.id}_${id._1}")
        case Num => Num.stringConverter[(String, Score)](id => (s"${id._2.id}${id._1}").hashCode)
        case Date => Date.stringConverter[(String, Score)](_ => LocalDateTime.now())
        case _ => throw new IllegalStateException("asd")
      }
      val overrid: ((String, Score)) => String = overrideDefaults.getOrElse(defFun.name, function)
      (defFun.name, defFun, overrid)
    }
    value1


  }


}

