package ru.vood.bigdata.generator.ent.metadata

import ru.vood.bigdata.generator.ent.Column
import ru.vood.bigdata.generator.ent.intf.EntityFun
import ru.vood.bigdata.generator.ent.intf.TypeColumn.{AF, HZ}
import ru.vood.bigdata.generator.ent.intf.ValueType.{Date, Num, Str}

//@Deprecated
object MetaDelete {

  lazy val getMeta = {

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

    //    заемщик

    val value2 = Set(
      Column("id", Str, HZ),
      Column("name", Num, AF),
      Column("surname", Date, HZ),
    )
    val value3 = value2 ++ s ++ n ++ d

    val clu = EntityFun(
      "clu",
      value3
    )


    Map(("score", score), ("clu", clu))

  }
}
