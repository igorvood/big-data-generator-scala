package ru.vood.bigdata.generator

import ru.vood.bigdata.generator.ent.clu.Clu
import ru.vood.bigdata.generator.ent.intf.{EntityFun, MetaDelete}
import ru.vood.bigdata.generator.ent.score.Score

import java.time.LocalDateTime

object Main {

  implicit val totalMeta: Map[String, EntityFun] = MetaDelete.getMeta

  def main(args: Array[String]): Unit = {
    val scoreMeta = totalMeta("score").cols.map { e => (e.name, e) }.toMap

    val scoreData = (1 to 2)
      .map { id =>
        val overrideScore = Map[String, String => String](("mer_sign", { q => q.hashCode.toString }), ("date_cr", { q => LocalDateTime.now().plusMinutes(q.hashCode).toString }))
        Score(id.toString, overrideScore, { _ => Set[Clu]() }) }
      .toSet

    val value = scoreData.map { q => q.csvStr }.mkString("\n")

    println(value)

  }

}

