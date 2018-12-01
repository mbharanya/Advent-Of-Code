import scala.io.Source

object Day1 extends App {
  var initialValue = 0
  val filename = "day1/input"
  for (line <- Source.fromFile(filename).getLines) {
    initialValue += line.toInt
  }
  println(initialValue)
}