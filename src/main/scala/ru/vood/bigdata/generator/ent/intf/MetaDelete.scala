package ru.vood.bigdata.generator.ent.intf

import ru.vood.bigdata.generator.ent.Column
import ru.vood.bigdata.generator.ent.intf.TypeColumn.{AF, HZ}
import ru.vood.bigdata.generator.ent.intf.ValueType.{Date, Num, Str}

//@Deprecated
object MetaDelete {

  def getMeta = {

    val score = EntityFun(
      "score",
      Set(
        Column("crm_id", Str, HZ),
        Column("mer_sign", Num, AF),
        Column("date_cr", Date, HZ),
      )
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
