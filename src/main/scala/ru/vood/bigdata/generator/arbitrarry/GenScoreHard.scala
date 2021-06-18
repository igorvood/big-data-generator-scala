package ru.vood.bigdata.generator.arbitrarry

import org.scalacheck.Gen
import ru.vood.bigdata.generator.arbitrarry.dict.Weighting.dictValue
import ru.vood.bigdata.generator.arbitrarry.dict.{WeightedDictionary, Weighting}
import ru.vood.bigdata.generator.arbitrarry.score.ScoreHard

case class GenScoreHard(id: Int) {

  val someDict: List[(Int, String)] = List((10, "VAL"), (90, "VAL__1"))

  private val weightedDictionary: WeightedDictionary[String] = Weighting.dictWeight(someDict)


  lazy val genScore: Gen[ScoreHard] = {
    val value: Gen[ScoreHard] = for {
      hash <- Gen.const(id.hashCode())
      hashStr <- Gen.const(hash.toString)
      id <- Gen.const(hash)
      col_1 <- Gen.const(hashStr)
      col_2 <- dictValue(hash, weightedDictionary)
      col_3 <- Gen.const(hash)
      col_4 <- Gen.const(hash)
    } yield ScoreHard(id, col_1, col_2, col_3, col_4)

    value
  }

}
