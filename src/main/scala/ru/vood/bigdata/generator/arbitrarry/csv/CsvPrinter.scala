package ru.vood.bigdata.generator.arbitrarry.csv

import java.io.{BufferedWriter, FileWriter}
import java.time.LocalDateTime

object CsvPrinter {

  /* @Deprecated
   def print[T](ent: String, cnt: Int, gen: Int => Option[T], toStr: T => String) = {
     val fileWriter: BufferedWriter = new BufferedWriter(new FileWriter(s"e:/temp/${ent}Hard.csv"))

     val time: LocalDateTime = LocalDateTime.now()
     println(s"write $ent => " + time)
     val value: Seq[Option[T]] = (1 to cnt).to(LazyList)
       .map(id => gen(id))
     value
       .filter(opt => opt match {
         case Some(_) => true
         case None => false
       })
       .map(opt => opt.get)
       .map(toStr)
       .foreach(q => fileWriter.write(q + "\n"))

     val time1 = LocalDateTime.now()
     println(s"write end $ent => " + time1)

     fileWriter.close()

     val totalNano = time1.getNano - time.getNano
     val persEnt = totalNano.toDouble / cnt.toDouble
     println(s"per $ent => $persEnt totalNano => $totalNano cnt => $cnt")

   }*/

  def print[T](ent: String, seq: ()=> LazyList[Option[T]], toStr: T => String) = {
    val fileWriter: BufferedWriter = new BufferedWriter(new FileWriter(s"e:/temp/${ent}Hard.csv"))

    val time: LocalDateTime = LocalDateTime.now()
    println(s"write $ent => " + time)
    var cnt = 0
    seq()
      .filter(opt => opt match {
        case Some(_) => true
        case None => false
      })
      .map(opt => {
        cnt = cnt + 1
        opt.get
      })
      .map(toStr)
      .foreach(q => fileWriter.write(q + "\n"))

    val time1 = LocalDateTime.now()
    println(s"write end $ent => " + time1)

    fileWriter.close()

    val totalNano = time1.getNano - time.getNano
    val persEnt = totalNano.toDouble / cnt.toDouble
    println(s"per $ent => $persEnt totalNano => $totalNano cnt => $cnt")
  }

}
