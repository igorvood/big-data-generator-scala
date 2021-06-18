package ru.vood.bigdata.generator.arbitrarry

import ru.vood.bigdata.generator.arbitrarry.clu.CluHard
import ru.vood.bigdata.generator.arbitrarry.csv.CsvPrinter
import ru.vood.bigdata.generator.arbitrarry.dict.{WeightedDictionary, Weighting}
import ru.vood.bigdata.generator.arbitrarry.score.{GenScoreHard, ScoreHard}

import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture

object Main {

  val someDict: List[(Int, String)] = (1 to 10).map(q => (q, "VAL_" + q)).toList

  private val weightedDictionary: WeightedDictionary[String] = Weighting.dictWeight(someDict)


  def main(args: Array[String]): Unit = {

    println("begin => " + LocalDateTime.now())
    println("======================")
    val cntScore = 1_000_000_000

    val score: () => LazyList[Option[ScoreHard]] = { () =>
      (1 to cntScore).to(LazyList).map(id => GenScoreHard(id, weightedDictionary).genScore.sample)
    }

    val clu: () => LazyList[Some[CluHard]] = { () =>
      score()
        .flatMap(sc => sc.get.clus)
        .map(q => Some(q))

    }

    val value = CompletableFuture.runAsync(() => {
      CsvPrinter.print[ScoreHard]("score", score, { score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4}" })
      println("======================")
    })
    val value1 = CompletableFuture.runAsync(() => {
      CsvPrinter.print[CluHard]("clu", clu, { clu => s"${clu.id};${clu.name};${clu.inn}" })
      println("======================")
    })


    val value3 = CompletableFuture.runAsync(() => {
      CsvPrinter.print[ScoreHard]("score_2", score, { score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4}" })
      println("======================")
    })
    val value4 = CompletableFuture.runAsync(() => {
      CsvPrinter.print[CluHard]("clu_2", clu, { clu => s"${clu.id};${clu.name};${clu.inn}" })
      println("======================")
    })

    val value5 = CompletableFuture.runAsync(() => {
      CsvPrinter.print[ScoreHard]("score_3", score, { score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4}" })
      println("======================")
    })
    val value6 = CompletableFuture.runAsync(() => {
      CsvPrinter.print[CluHard]("clu_3", clu, { clu => s"${clu.id};${clu.name};${clu.inn}" })
      println("======================")
    })

    val value7 = CompletableFuture.runAsync(() => {
      CsvPrinter.print[ScoreHard]("score_4", score, { score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4}" })
      println("======================")
    })
    val value8 = CompletableFuture.runAsync(() => {
      CsvPrinter.print[CluHard]("clu_4", clu, { clu => s"${clu.id};${clu.name};${clu.inn}" })
      println("======================")
    })

    value.join()
    value1.join()
    value4.join()
    value3.join()
    value5.join()
    value6.join()
    value7.join()
    value8.join()

    println("end => " + LocalDateTime.now())
  }

}
