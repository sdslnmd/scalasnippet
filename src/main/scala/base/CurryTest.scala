package base

case class CurryContainer(x: Int)

object CurryTest {

  def create()(f: CurryContainer => Boolean): Boolean = {
    val container = CurryContainer(2)
    f(container)
  }

  var getInt: Int => String = x => "String " + x.toString

  def main(args: Array[String]): Unit = {

    val intToContainer = create() { container =>
      if (container.x > 3) true else false
    }

    println(s"intToContainer $intToContainer")

    println("call get int " + getInt(123))

  }

}
