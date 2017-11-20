package implicithello

object ImplicitTest {}

object Json {

  def Str(string: String) = ???
  def Num(string: Double) = ???
  def Num(string: Int) = ???
}

class Json {}

trait Jsonable[T] {
  def serialize(t: T): Json
}

object Jsonable {

  implicit object StringJsonable extends Jsonable[String] {
    def serialize(t: String) = Json.Str(t)
  }

  implicit object DoubleJsonable extends Jsonable[Double] {
    def serialize(t: Double) = Json.Num(t)
  }

  implicit object IntJsonable extends Jsonable[Int] {
    def serialize(t: Int) = Json.Num(t.toDouble)
  }

  def convertToJson[T: Jsonable](x: T): Json = {
    implicitly[Jsonable[T]].serialize(x)
  }


}
