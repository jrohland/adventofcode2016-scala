/**
  * Created by jesse on 12/1/2016.
  */
object Day01Part1 {

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day01-input.txt").getLines().next().split(",")

    val finalPosition = input.map(movement => {
      val direction = movement.trim.charAt(0) match {
        case 'L' => -1
        case 'R' => 1
      }
      val distance = movement.trim.substring(1).toInt

      (direction, distance)
    }).foldLeft((0, (0, 0)))((location, movement) => {

      val direction = location._1 + movement._1 match {
        case -1 => 3
        case 4 => 0
        case _ => location._1 + movement._1
      }

      val position = direction match {
        case 0 => (location._2._1, location._2._2 + movement._2)
        case 1 => (location._2._1 + movement._2, location._2._2)
        case 2 => (location._2._1, location._2._2 - movement._2)
        case 3 => (location._2._1 - movement._2, location._2._2)
      }

      (direction, position)
    })._2

    println(s"${finalPosition._1.abs} blocks ${if (finalPosition._1 >= 0) "north" else "south"}")
    println(s"${finalPosition._2.abs} blocks ${if (finalPosition._2 >= 0) "east" else "west"}")
    println(s"${finalPosition._1.abs + finalPosition._2.abs} blocks away")

  }

}
