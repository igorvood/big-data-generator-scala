package ru.vood.bigdata.generator.ent.intf

import ru.vood.bigdata.generator.ent.Column
import ru.vood.bigdata.generator.ent.intf.TypeColumn.{AF, HZ}
import ru.vood.bigdata.generator.ent.intf.ValueType.{Date, Num, Str}

//@Deprecated
object MetaDelete {

  def getMeta = {

    val value = Set(
      Column("crm_id", Str, HZ),
      Column("mer_sign", Num, AF),
      Column("date_cr", Date, HZ),
    )
    val s = (1 to 30).map { q => Column("col_s_" + q, Str, HZ) }
    val n = (1 to 30).map { q => Column("col_n_" + q, Num, AF) }
    val d = (1 to 30).map { q => Column("col_d_" + q, Date, AF) }

    val value1 = value ++ s ++ n ++ d

    val score = EntityFun(
      "score",
      value1
    )

    val clu = EntityFun(
      "clu",
      Set(
        Column("id", Str, HZ),
        Column("name", Num, AF),
        Column("surname", Date, HZ),
      )
    )


    Map(("score", score), ("clu", clu))

  }
}
