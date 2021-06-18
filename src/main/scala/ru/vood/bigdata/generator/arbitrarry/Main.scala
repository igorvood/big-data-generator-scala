package ru.vood.bigdata.generator.arbitrarry

import ru.vood.bigdata.generator.arbitrarry.dict.{WeightedDictionary, Weighting}

import java.io.{BufferedWriter, FileWriter}
import java.time.LocalDateTime

object Main {

  /* def print[T](cnt: Int, gen: Gen[T], prn: T=>String)={
     val set = (1 to cnt)
       .map(id => GenScoreHard(id))
       .map(score => score.genScore.sample)
       .filter(opt => opt match {
         case Some(_) => true
         case None => false
       })
       .map( opt => opt.get )
       .toSet

   }*/
  val someDict: List[(Int, String)] = (1 to 10).map(q => (q, "VAL_" + q)).toList

  private val weightedDictionary: WeightedDictionary[String] = Weighting.dictWeight(someDict)


  def main(args: Array[String]): Unit = {
    val writerClu: BufferedWriter = new BufferedWriter(new FileWriter("e:/temp/scoreHard.csv"))

    val time: LocalDateTime = LocalDateTime.now()
    println("write score => " + time)

    val cnt = 200_000_000 //0000000
    (1 to cnt).to(LazyList)
      .map(id => GenScoreHard(id, weightedDictionary))
      .map(score => score.genScore.sample)
      .filter(opt => opt match {
        case Some(_) => true
        case None => false
      })
      .map(opt => opt.get)
      .map(score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4}")
      .foreach(q => writerClu.write(q + "\n"))

    val time1 = LocalDateTime.now()
    println("write end => " + time1)

    writerClu.close()

    val totalNano = time1.getNano - time.getNano
    val perscore = totalNano.toDouble / cnt.toDouble
    println("perscore => " + perscore + " totalNano => " + totalNano + " cnt =>" + cnt)


  }

}
