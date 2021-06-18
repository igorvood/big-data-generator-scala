package ru.vood.bigdata.generator.arbitrarry.score

case class ScoreHard(
                      id: Int,
                      col_1: String,
                      col_2: String,
                      col_3: String,
                      col_4: Int
                    ) {


  require(id > 0)
  require(col_2.contains("VAL_"), {
    "must contains VAL_ current val => " + col_1
  })
  require(col_3 == ("VAL_" + id), {
    "must equal VAL_" + id + " current val col_2 => " + col_3
  })

}
