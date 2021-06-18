package ru.vood.bigdata.generator.arbitrarry.clu

import org.scalacheck.Gen

case class GenCluHard(idClu: Int, idScore: Int) {

  def strToSize(str: String, cnt: Int, char: String): String = {
    assert(str.length <= cnt, {
      s"Size String must be less than $cnt, current size is ${str.length}"
    })
    val str1 = (1 to cnt - str.length).map(_ => char).mkString("")
    s"$str$str1"
  }

  lazy val genClu: Gen[CluHard] = {
    val value: Gen[CluHard] = for {
      id <- Gen.const(idClu match {
        case 1 => s"$idScore"
        case _ => s"${idScore}_$idClu"
      })
      name <- Gen.const(s"${id}_name")
      inn <- Gen.const(strToSize(s"$idScore$idClu", 12, "0"))
      //      hashStr <- Gen.const(hash.toString.hashCode.toString)
      //      col_1 <- Gen.const(hashStr)
      //      col_2 <- dictValue(hash, weightedDictionary)
      //      col_3 <- Gen.const("VAL_" + id)
      //      col_4 <- Gen.const(hash)
    } yield CluHard(id, name, inn)
    value
  }

}
