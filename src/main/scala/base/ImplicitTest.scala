package base

object ImplicitTest extends App {

  trait PrintOps {
    val value: String
    def printWithSeperator(seq: String): Unit = {
      println(value.split("").mkString(seq))
    }
  }

  implicit def stringToPrintOps(str: String): PrintOps = new PrintOps {
    override val value: String = str
  }

  implicit def intToPrintOps(int: Int): PrintOps = new PrintOps {
    override val value: String = int.toString
  }

  "hello,world" printWithSeperator "*"

  123 printWithSeperator "*"

}
