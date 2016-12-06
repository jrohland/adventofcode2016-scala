/**
  * Created by jesse on 12/6/2016.
  */
object Day04Part1 {

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day04-input.txt").getLines()

    val sectors = input.map(Room).filter(_.isValid).map(_.sectorId).sum

    println(sectors)
  }

  case class Room(roomCode: String) {

    val name = roomCode.substring(0, roomCode.lastIndexOf('-')).split('-').mkString
    val sectorId = roomCode.substring(roomCode.lastIndexOf('-') + 1, roomCode.indexOf('[')).toInt
    val checksum = roomCode.substring(roomCode.indexOf('[') + 1, roomCode.length - 1)

    def isValid: Boolean = {
      val charOccurrence = name.toCharArray.map(char => {
        char -> 1
      }).groupBy(_._1).mapValues(_.map(_._2).sum)

      val correctCheckSum = charOccurrence.toSeq.sortBy(char => {
        (-char._2, char._1)
      }).toList.slice(0, 5).map(_._1).mkString

      correctCheckSum.equals(checksum)
    }



  }

}
