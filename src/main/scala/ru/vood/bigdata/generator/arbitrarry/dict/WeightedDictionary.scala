package ru.vood.bigdata.generator.arbitrarry.dict

import scala.collection.immutable.TreeMap

case class WeightedDictionary[T](sumWeight: Int, dict: TreeMap[Int, T])
