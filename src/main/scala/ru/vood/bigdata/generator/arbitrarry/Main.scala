package ru.vood.bigdata.generator.arbitrarry

import ru.vood.bigdata.generator.arbitrarry.csv.CsvPrinter
import ru.vood.bigdata.generator.arbitrarry.dict.{WeightedDictionary, Weighting}
import ru.vood.bigdata.generator.arbitrarry.score.ScoreHard

object Main {

  val someDict: List[(Int, String)] = (1 to 10).map(q => (q, "VAL_" + q)).toList

  private val weightedDictionary: WeightedDictionary[String] = Weighting.dictWeight(someDict)


  def main(args: Array[String]): Unit = {
    CsvPrinter.print[ScoreHard]("score", 20, { id => GenScoreHard(id, weightedDictionary).genScore.sample },
      { score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4}" }
    )


  }

}
