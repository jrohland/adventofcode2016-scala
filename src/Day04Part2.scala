/**
  * Created by jesse on 12/6/2016.
  */
object Day04Part2 {

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day04-input.txt").getLines()

    val validRooms = input.map(Room).filter(_.isValid)

    validRooms.foreach(room => {
      println(s"${room.unencryptedName}\t ${room.sectorId}")
    })
  }

  case class Room(roomCode: String) {

    val name = roomCode.substring(0, roomCode.lastIndexOf('-')).split('-').mkString(" ")
    val sectorId = roomCode.substring(roomCode.lastIndexOf('-') + 1, roomCode.indexOf('[')).toInt
    val checksum = roomCode.substring(roomCode.indexOf('[') + 1, roomCode.length - 1)

    def unencryptedName: String = {
      name.toCharArray.map({
        case ' ' => ' '
        case char =>
          (((char.toInt - 97 + sectorId) % 26) + 97).toChar
      }).mkString
    }

    def isValid: Boolean = {
      val charOccurrence = name.replaceAll(" ", "").toCharArray.map(char => {
        char -> 1
      }).groupBy(_._1).mapValues(_.map(_._2).sum)

      val correctCheckSum = charOccurrence.toSeq.sortBy(char => {
        (-char._2, char._1)
      }).toList.slice(0, 5).map(_._1).mkString

      correctCheckSum.equals(checksum)
    }



  }

}
