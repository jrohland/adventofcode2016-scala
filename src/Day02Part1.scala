/**
  * Created by jesse on 12/2/2016.
  */
object Day02Part1 {

  val moves = Map(
    ("1", 'R') -> "2",
    ("1", 'D') -> "4",
    ("2", 'R') -> "3",
    ("2", 'L') -> "1",
    ("2", 'D') -> "5",
    ("3", 'D') -> "6",
    ("3", 'L') -> "2",

    ("4", 'R') -> "5",
    ("4", 'U') -> "4",
    ("4", 'D') -> "7",
    ("5", 'R') -> "6",
    ("5", 'L') -> "4",
    ("5", 'U') -> "2",
    ("5", 'D') -> "8",
    ("6", 'L') -> "5",
    ("6", 'U') -> "3",
    ("6", 'D') -> "9",

    ("7", 'R') -> "8",
    ("7", 'U') -> "4",
    ("8", 'R') -> "9",
    ("8", 'L') -> "7",
    ("8", 'U') -> "5",
    ("9", 'U') -> "6",
    ("9", 'L') -> "8"
  )

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day02-input.txt").getLines()

    val code = input.map(line => {
      line.toCharArray.foldLeft("5")((currentDigit, direction) => {
        moves.get((currentDigit, direction)) match {
          case Some(digit) => digit
          case None => currentDigit
        }
      })
    }).mkString

    println(s"Code is: $code")
  }

}
