/**
  * Created by jesse on 12/1/2016.
  */
object Day01Part2 {

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day01-input.txt").getLines().next().split(",")

    val visitedLocations = input.map(movement => {
      val direction = movement.trim.charAt(0) match {
        case 'L' => -1
        case 'R' => 1
      }
      val distance = movement.trim.substring(1).toInt

      (direction, distance)
    }).foldLeft((0, List[(Int, Int)]((0,0))))((location, movement) => {

      val direction = location._1 + movement._1 match {
        case -1 => 3
        case 4 => 0
        case _ => location._1 + movement._1
      }

      val lastLocation = location._2.last

      val visitedLocations = (1 to movement._2).map(i => {
        direction match {
          case 0 => (lastLocation._1, lastLocation._2 + i)
          case 1 => (lastLocation._1 + i, lastLocation._2)
          case 2 => (lastLocation._1, lastLocation._2 - i)
          case 3 => (lastLocation._1 - i, lastLocation._2)
        }
      }).toList

      (direction, location._2 ::: visitedLocations)
    })._2

    val repeatedLocationsIndexes = (1 until visitedLocations.length).flatMap(i => {
      if (visitedLocations.slice(0, i).contains(visitedLocations(i))) Option(i) else None
    })
    val firstRepeatedLocation = visitedLocations(repeatedLocationsIndexes.head)

    println(s"${firstRepeatedLocation._1.abs} blocks ${if (firstRepeatedLocation._1 >= 0) "north" else "south"}")
    println(s"${firstRepeatedLocation._2.abs} blocks ${if (firstRepeatedLocation._2 >= 0) "east" else "west"}")
    println(s"${firstRepeatedLocation._1.abs + firstRepeatedLocation._2.abs} blocks away")

  }

}
