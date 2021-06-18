package ru.vood.bigdata.generator.arbitrarry

import java.io.{BufferedWriter, FileWriter}

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


  def main(args: Array[String]): Unit = {
    val writerClu: BufferedWriter = new BufferedWriter(new FileWriter("e:/temp/scoreHard.csv"))

    (1 to 20)
      .map(id => GenScoreHard(id))
      .map(score => score.genScore.sample)
      .filter(opt => opt match {
        case Some(_) => true
        case None => false
      })
      .map(opt => opt.get)
      .map(score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4}")
      .foreach(q => writerClu.write(q + "\n"))
    writerClu.close()

  }

}
