/**
  * Created by jesse on 12/3/2016.
  */
object Day03Part2 {

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day03-input.txt").getLines()

    val brokenTriangles = input.map(lineToTriangle).toList

    val validTriangles = (0 to brokenTriangles.length by 3).flatMap(i => {
      try {
        val input1 = brokenTriangles(i)
        val input2 = brokenTriangles(i + 1)
        val input3 = brokenTriangles(i + 2)

        List(
          Triangle(input1.a, input2.a, input3.a),
          Triangle(input1.b, input2.b, input3.b),
          Triangle(input1.c, input2.c, input3.c)
        )
      } catch {
        case e: IndexOutOfBoundsException => List()
      }

    }).filter(_.valid)

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
