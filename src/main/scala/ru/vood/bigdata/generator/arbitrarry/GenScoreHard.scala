package ru.vood.bigdata.generator.arbitrarry

import org.scalacheck.Gen
import ru.vood.bigdata.generator.arbitrarry.dict.WeightedDictionary
import ru.vood.bigdata.generator.arbitrarry.dict.Weighting.dictValue
import ru.vood.bigdata.generator.arbitrarry.score.ScoreHard

case class GenScoreHard(id: Int, weightedDictionary: WeightedDictionary[String]) {

  lazy val genScore: Gen[ScoreHard] = {
    val value: Gen[ScoreHard] = for {
      hash <- Gen.const(id.hashCode())
      hashStr <- Gen.const(hash.toString.hashCode.toString)
      col_1 <- Gen.const(hashStr)
      col_2 <- dictValue(hash, weightedDictionary)
      col_3 <- Gen.const("VAL_" + id)
      col_4 <- Gen.const(hash)
    } yield ScoreHard(id, col_1, col_2, col_3, col_4)

    value
  }

}
