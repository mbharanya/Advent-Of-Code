import scala.collection.mutable
import scala.io.Source

object Day1 extends App {
  var initialValue = 0
  val filename = "day1/input"

  val alreadyFoundMap = mutable.Set[Integer]()

  val lines: List[String] = Source.fromFile(filename).getLines.toList

    Stream.continually(lines.toStream).takeWhile(lines => {
          lines.toList.exists( line => {
            val foundVal = line.toInt
            initialValue += foundVal
            if (alreadyFoundMap.contains(initialValue)) {
              println(initialValue)
              System.exit(0)
              true
            } else {
              alreadyFoundMap += initialValue
              false
            }
          })
      lines != null
    }
    ).mkString(",")


}