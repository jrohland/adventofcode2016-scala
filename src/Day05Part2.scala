import java.security.MessageDigest

/**
  * Created by jesse on 12/6/2016.
  */
object Day05Part2 {

  val digest = MessageDigest.getInstance("MD5")

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day05-input.txt").getLines().next()

    val code = findFullCode(input)

    println(s"code is $code")

  }

  def findHash(code: String, startingPoint: Int): (Int, Int, Char) = {
    Stream.from(startingPoint).foreach(i => {
      val md5 = digest.digest(s"$code$i".getBytes).map("%02x".format(_)).mkString
      if (md5.startsWith("00000") && '0' <= md5.charAt(5) && md5.charAt(5) <= '7')
        return (i, md5.charAt(5).toString.toInt, md5.charAt(6))
    })

    (-1, -1, ' ')
  }

  def findFullCode(code: String): String = {
    Stream.from(1).foldLeft(-1, Map.empty[Int, Char])((current, i) => {
      println("Looking for next hash")
      val nextHash = findHash(code, current._1 + 1)
      if (current._2.keySet.contains(nextHash._2)) {
        println(s"Found repeat char for index: ${nextHash._2}")
        (nextHash._1, current._2)
      } else {
        println(s"Found char for index: ${nextHash._2}")
        val newMap = current._2 + (nextHash._2 -> nextHash._3)

        if (newMap.size == 8) {
          return (0 to 7).map(newMap(_)).mkString
        }

        (nextHash._1, newMap)
      }
    })

    ""
  }

}
