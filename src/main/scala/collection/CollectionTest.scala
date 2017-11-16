package collection

object CollectionTest {


  def main(args: Array[String]): Unit = {

    val s = Seq(1, 2, 3)

    val charst = s.flatMap(x => String.valueOf(x))

    val anyList:List[Any]= List("123", 123)

    anyList.foreach {
      case yyy:Char=> println("char")
      case zzz:String=> println("String")
      case zzz:Int=> println("int")
    }

  }


}
