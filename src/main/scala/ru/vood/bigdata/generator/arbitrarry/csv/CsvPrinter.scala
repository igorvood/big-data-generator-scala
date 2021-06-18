package ru.vood.bigdata.generator.arbitrarry.csv

import java.io.{BufferedWriter, FileWriter}
import java.time.LocalDateTime

object CsvPrinter {

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

}
