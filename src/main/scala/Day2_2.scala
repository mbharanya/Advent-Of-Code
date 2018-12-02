import scala.io.Source

object Day2_2 extends App {
  val filename = "day2/input"

  val time = System.nanoTime()


  val result = Source.fromFile(filename).getLines
    .toList
    .combinations(2)
    .map(tuple => {
      (tuple.head.length - 1, createDiffString(tuple.head, tuple.last))
    })
    .find(a => a._1 == a._2.length)
    .map(_._2)
    .getOrElse("")

  val endTime = System.nanoTime()

  println(result)
  println("RunTime: " + (endTime - time)/1000000 + "ms")

  private def createDiffString(first: String, second: String) = {
    val diff = first
      .zipWithIndex
      .filter(a => a._1 == second(a._2))
      .map(_._1)
      .foldLeft("")(_ + _)

    diff
  }
}
