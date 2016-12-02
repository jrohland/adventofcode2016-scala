/**
  * Created by jesse on 12/2/2016.
  */
object Day02Part2 {

  val moves = Map(
    ("1", 'D') -> "3",

    ("2", 'D') -> "6",
    ("2", 'R') -> "3",

    ("3", 'U') -> "1",
    ("3", 'D') -> "7",
    ("3", 'L') -> "2",
    ("3", 'R') -> "4",

    ("4", 'D') -> "8",
    ("4", 'L') -> "3",

    ("5", 'R') -> "6",

    ("6", 'U') -> "2",
    ("6", 'D') -> "A",
    ("6", 'L') -> "5",
    ("6", 'R') -> "7",

    ("7", 'U') -> "3",
    ("7", 'D') -> "B",
    ("7", 'L') -> "6",
    ("7", 'R') -> "8",

    ("8", 'U') -> "4",
    ("8", 'D') -> "C",
    ("8", 'R') -> "9",
    ("8", 'L') -> "7",

    ("9", 'L') -> "8",

    ("A", 'U') -> "6",
    ("A", 'R') -> "B",

    ("B", 'U') -> "7",
    ("B", 'D') -> "D",
    ("B", 'L') -> "A",
    ("B", 'R') -> "C",

    ("C", 'U') -> "8",
    ("C", 'L') -> "B",

    ("D", 'U') -> "B"
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
