/**
  * Created by jesse on 12/6/2016.
  */
object Day06Part1 {

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day06-input.txt").getLines()

    val codeMap = input.flatMap(line => {
      (0 until line.length).map(i => {
        i -> line.charAt(i)
      })
    }).toList.groupBy(_._1).mapValues(chars => {
      chars.map(_._2).groupBy(identity).mapValues(_.size).toSeq.sortBy(-_._2).head._1
    })

    val code = (0 until codeMap.size).map(codeMap(_)).mkString

    println(code)

  }

}
