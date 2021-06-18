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
      col_5 <- Gen.const(hash)
      col_6 <- Gen.const(hash)
      col_7 <- Gen.const(hash)
      col_8 <- Gen.const(hash)
      col_9 <- Gen.const(hash)
      col_10 <- Gen.const(hash)
      col_11 <- Gen.const(hash)
      col_12 <- Gen.const(hash)
      col_13 <- Gen.const(hash)
      col_14 <- Gen.const(hash)
      col_15 <- Gen.const(hash)
      col_16 <- Gen.const(hash)
      col_17 <- Gen.const(hash)
      col_18 <- Gen.const(hash)
      col_19 <- Gen.const(hash)
      col_20 <- Gen.const(hash)
    } yield ScoreHard(id, col_1, col_2, col_3,
      col_4,
      col_5,
      col_6,
      col_7,
      col_8,
      col_9,
      col_10,
      col_11,
      col_12,
      col_13,
      col_14,
      col_15,
      col_16,
      col_17,
      col_18,
      col_19,
      col_20
    )

    value
  }

}
