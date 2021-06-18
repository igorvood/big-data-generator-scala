package ru.vood.bigdata.generator.arbitrarry.dict

import org.scalacheck.Gen

import scala.collection.immutable.TreeMap

object Weighting {

  /* implicit def dictWeight[T](dict: List[(Int, T)]): WeightedDictionary[T] = {
     var total = 0L
     val builder = TreeMap.newBuilder[Int, Gen[T]]
     dict.foreach { case (weight, value) =>
       total += weight
       builder += (total, value)
     }
     WeightedDictionary(dict.map(q => q._1).sum, builder.result)
   }*/

  implicit def dictWeight[T]: List[(Int, T)] => WeightedDictionary[T] = { dict =>
    var total = 0
    val builder = TreeMap.newBuilder[Int, T]
    dict.foreach { case (weight, value) =>
      total.+=(weight)
      builder.addOne(total, value)
    }
    val sum = dict.map(q => q._1).sum
    val result = builder.result()
    WeightedDictionary(sum, result)
  }


  def dictValue[T](hash: Int, dict: List[(Int, T)])(implicit wDict: List[(Int, T)] => WeightedDictionary[T]): Gen[T] = {
    dictValue(hash, wDict(dict))
  }

  def dictValue[T](hash: Int, dict: WeightedDictionary[T]): Gen[T] = {
    dict.dict.rangeFrom(hash % dict.sumWeight).head._2
  }

}
