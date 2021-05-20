package com.cqx.stn.spark.WordCount

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    var rdd = sc.textFile("/opt/spark-2.4.7-bin-hadoop2.7/bin/spark-shell")
    var wordCount = rdd.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)
    var wordSort = wordCount.map(x => (x._2, x._1)).sortByKey(false).map(x => (x._2, x._1))
    wordSort.saveAsTextFile("/tmp/spark")
    sc.stop()

  }

}
