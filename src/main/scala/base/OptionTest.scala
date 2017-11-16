package base

object OptionTest {

  //https://softwareengineering.stackexchange.com/questions/255696/what-is-a-lifted-representation

  def main(args: Array[String]): Unit = {

    def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f

    def lift2[A, B](f: A => B): Option[A] => Option[B] = {
      x => if (x.isEmpty) None else Some(f(x.get))
    }

    def lift3[A, B](f: A => B): Stream[A] => Stream[B] = {
      val empty = Stream.empty[B]
      x => {
        x.foreach(zz => {
//          empty.append(f(zz))
        })
        empty
      }
    }



    def fun1[A](x: A): A = x

    def t1(x: Int): String = {
      String.valueOf(x)
    }

    println(lift(t1)(Option.empty))

    println(saySomething("1")("2"))

  }

  def saySomething1(prefix: String) = (s: String) => {
    prefix + " " + s
  }


  def saySomething(prefix: String): String => String = (s: String) => {
    prefix + " " + s
  }

  def f(x: Int): Int => Int = { n: Int => x + n }

  def f2(x: Int) = (y: Int) => x + y

}
