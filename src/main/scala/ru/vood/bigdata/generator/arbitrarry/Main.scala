package ru.vood.bigdata.generator.arbitrarry

import ru.vood.bigdata.generator.arbitrarry.dict.{WeightedDictionary, Weighting}
import ru.vood.bigdata.generator.arbitrarry.score.ScoreHard

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


  def print[T](ent: String, cnt: Int, gen: Int => Option[T], toStr: T => String) = {
    val writerClu: BufferedWriter = new BufferedWriter(new FileWriter(s"e:/temp/${ent}Hard.csv"))

    val time: LocalDateTime = LocalDateTime.now()
    println(s"write $ent => " + time)
    (1 to cnt).to(LazyList)
      .map(id => gen(id))
      .filter(opt => opt match {
        case Some(_) => true
        case None => false
      })
      .map(opt => opt.get)
      .map(toStr)
      .foreach(q => writerClu.write(q + "\n"))

    val time1 = LocalDateTime.now()
    println(s"write end $ent => " + time1)

    writerClu.close()

    val totalNano = (time1.getNano - time.getNano)
    val perscore = totalNano.toDouble / cnt.toDouble
    println(s"per $ent => $perscore totalNano => $totalNano cnt => $cnt")

  }

  def main(args: Array[String]): Unit = {
    print[ScoreHard]("score", 20_000_000, { id => GenScoreHard(id, weightedDictionary).genScore.sample },
      { score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4};${score.col_5};${score.col_6};${score.col_7};${score.col_8};${score.col_9};${score.col_10};${score.col_11};${score.col_12};${score.col_13};${score.col_14};${score.col_15};${score.col_16};${score.col_17};${score.col_18};${score.col_19};${score.col_20}" }
    )

    /* val writerClu: BufferedWriter = new BufferedWriter(new FileWriter("e:/temp/scoreHard.csv"))

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
       .map(score => s"${score.id};${score.col_1};${score.col_2};${score.col_3};${score.col_4};${score.col_5};${score.col_6};${score.col_7};${score.col_8};${score.col_9};${score.col_10};${score.col_11};${score.col_12};${score.col_13};${score.col_14};${score.col_15};${score.col_16};${score.col_17};${score.col_18};${score.col_19};${score.col_20}")
       .foreach(q => writerClu.write(q + "\n"))

     val time1 = LocalDateTime.now()
     println("write end => " + time1)

     writerClu.close()

     val totalNano = time1.getNano - time.getNano
     val perscore = totalNano.toDouble / cnt.toDouble
     println("perscore => " + perscore + " totalNano => " + totalNano + " cnt =>" + cnt)*/


  }

}
