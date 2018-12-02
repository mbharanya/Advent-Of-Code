import scala.io.Source

object Day2 extends App {
  val filename = "day2/input"

  var twoTimes = 0
  var threeTimes = 0
  for (line <- Source.fromFile(filename).getLines) {
    if (containsLetterExactlyNTimes(line, 2)) twoTimes += 1
    if (containsLetterExactlyNTimes(line, 3)) threeTimes += 1
  }

  println(twoTimes * threeTimes)

  def containsLetterExactlyNTimes(input: String, times: Int): Boolean = {
    val hasFoundNoLetters = input.groupBy(_.toChar).mapValues(_.size).filter(_._2 == times).map(_._1).toList.isEmpty
    !hasFoundNoLetters
  }
}
