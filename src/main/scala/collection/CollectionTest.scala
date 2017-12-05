package collection

object CollectionTest {

  def trans(m: Map[String, Map[String, List[String]]]): List[(String, String, String)] = {
    for {
      (k, m2) <- m
      (k2, list) <- m2
      item <- list
    } yield (k, k2, item)
  }.toList

  def main(args: Array[String]): Unit = {

    val list = trans(
      Map("a" -> Map("b" -> List("1", "2")), "c" -> Map("d" -> List("3", "4"))))

    println("trans list to " + list)

    val s = Seq(1, 2, 3)

    val charst = s.flatMap(x => String.valueOf(x))

    val anyList: List[Any] = List("123", 123)

    anyList.foreach {
      case yyy: Char   => println("char")
      case zzz: String => println("String")
      case zzz: Int    => println("int")
    }

    val tuples = List(1, 2, 3).flatMap(x => Some('x').map(y => (x, y)))
    println(list)

    val tuples2 = for {
      x <- List(1, 2, 3)
      y <- "x"
    } yield (x, y)

    println("tuples2 123123123")

    val ints = Vector(1, 2, 3)
    ints ++ Vector(4, 9)

    println(ints)

  }

}
