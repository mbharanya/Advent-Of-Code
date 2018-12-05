
import scala.collection.mutable
import scala.io.Source

object Day3 extends App {
  val filename = "day3/input"
  val time = System.nanoTime()

  val fieldSize = 1000

  case class Rectangle(id: Int, offsetX: Int, offsetY: Int, sizeX: Int, sizeY: Int)

  private val rectangleRegex = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".r

  def parseRectangle(s: String): Rectangle = s match {
    case rectangleRegex(id, left, top, width, height) => Rectangle(id.toInt, left.toInt, top.toInt, width.toInt, height.toInt)
  }

  def parseRectangles(input: String): Seq[Rectangle] = input.lines.map(parseRectangle).toSeq


  val start = System.nanoTime()
  val recs = Source.fromFile(filename).getLines.map(parseRectangle).toList

  println(getNonOverlaps(recs))

  println("RunTime (ms): "+(System.nanoTime() - start)/1000000)

  def getOverlaps(recs: Seq[Rectangle]): Int = {
    val overlaps: mutable.Map[(Int, Int), Int] = mutable.Map.empty.withDefaultValue(0)

    for {
      Rectangle(_, left, top, width, height) <- recs.toSet
      x <- left until (left + width)
      y <- top until (top + height)
    } {
      val pos = (x, y)
      overlaps(pos) += 1
    }

    overlaps.values.count(_ >= 2)
  }

  def getNonOverlaps(recs: Seq[Rectangle]): Int = {
    val overlaps: mutable.Map[(Int, Int), Rectangle] = mutable.Map.empty
    val nonOverlaps: mutable.Set[Rectangle] = recs.to[mutable.Set]

    for {
      rect@Rectangle(_, left, top, width, height) <- recs.toSet
      x <- left until (left + width)
      y <- top until (top + height)
    } {
      val pos = (x, y)
      if (overlaps.contains(pos)) {
        nonOverlaps -= rect
        nonOverlaps -= overlaps(pos)
      }
      else
        overlaps(pos) = rect
    }

    nonOverlaps.head.id
  }

}
