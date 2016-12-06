import java.security.MessageDigest

/**
  * Created by jesse on 12/6/2016.
  */
object Day05Part1 {

  val digest = MessageDigest.getInstance("MD5")

  def main(args: Array[String]): Unit = {

    val input = scala.io.Source.fromFile("input/day05-input.txt").getLines().next()

    val code = (1 to 8).foldLeft(0, "")((current, i) => {
      println(s"looking for char for index $i")

      val nextHash = findHash(input, current._1 + 1)
      val currentCode = s"${current._2}${nextHash._2}"

      println(s"char found at index ${nextHash._1}")

      (nextHash._1 + 1, currentCode)
    })._2

    println(s"code is $code")

  }

  def findHash(code: String, startingPoint: Int): (Int, Char) = {
    Stream.from(startingPoint).foreach(i => {
      val md5 = digest.digest(s"$code$i".getBytes).map("%02x".format(_)).mkString
      if (md5.startsWith("00000"))
        return (i, md5.charAt(5))
    })

    (-1, ' ')
  }

}
