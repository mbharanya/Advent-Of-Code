import scala.collection.mutable
import scala.io.Source

object Day1 extends App {
  var initialValue = 0
  val filename = "day1/input"

  val alreadyFoundMap = mutable.Set[Integer]()

  val lines: List[String] = Source.fromFile(filename).getLines.toList

  Stream.continually(lines.toStream).takeWhile((lines: Stream[String]) => {
    println(lines.toList)
    !lines.toList.exists(line => {
      val foundVal = line.toInt
      initialValue += foundVal
      if (alreadyFoundMap.contains(initialValue)) {
        println(initialValue)
        true
      } else {
        alreadyFoundMap += initialValue
        false
      }
    })
  }
  ).mkString


}