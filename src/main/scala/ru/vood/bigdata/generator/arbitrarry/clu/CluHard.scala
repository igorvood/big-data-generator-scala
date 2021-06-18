package ru.vood.bigdata.generator.arbitrarry.clu

case class CluHard(id: String,
                   name: String,
                   inn: String
                  ) {

  require(inn.length == 9 || inn.length == 12, {
    s"inn must be length 9 or 12 current length is ${inn.length}"
  })
}
