/**
  * Created by jesse on 12/3/2016.
  */
object Day03Part1 {

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day03-input.txt").getLines()

    val validTriangles = input.map(lineToTriangle).filter(_.valid)

    println(s"${validTriangles.length} valid triangles")
  }

  def lineToTriangle(line: String): Triangle = {
    val trimedLine = line.trim
    val firstBreak = trimedLine.indexOf(' ')
    val secondPart = trimedLine.substring(firstBreak).trim
    val secondBreak = secondPart.indexOf(' ')

    Triangle(
      trimedLine.substring(0, firstBreak).toInt,
      secondPart.substring(0, secondBreak).toInt,
      secondPart.substring(secondBreak).trim.toInt)
  }

  case class Triangle(a: Int, b: Int, c: Int) {

    def valid: Boolean = {
      (a + b) > c && (b + c) > a && (a + c) > b
    }

  }

}
