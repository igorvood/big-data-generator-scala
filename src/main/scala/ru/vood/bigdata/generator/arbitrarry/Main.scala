package ru.vood.bigdata.generator.arbitrarry

import ru.vood.bigdata.generator.arbitrarry.csv.CsvPrinter
import ru.vood.bigdata.generator.arbitrarry.dict.{WeightedDictionary, Weighting}
import ru.vood.bigdata.generator.arbitrarry.score.ScoreHard

object Main {

  val someDict: List[(Int, String)] = (1 to 10).map(q => (q, "VAL_" + q)).toList

  private val weightedDictionary: WeightedDictionary[String] = Weighting.dictWeight(someDict)


  def main(args: Array[String]): Unit = {
    CsvPrinter.print[ScoreHard]("score", 20, { id => GenScoreHard(id, weightedDictionary).genScore.sample },
      { score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4};${score.col_5};${score.col_6};${score.col_7};${score.col_8};${score.col_9};${score.col_10};${score.col_11};${score.col_12};${score.col_13};${score.col_14};${score.col_15};${score.col_16};${score.col_17};${score.col_18};${score.col_19};${score.col_20}" }
    )


  }

}
