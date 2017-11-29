package base

import java.util.Date

object FunctionTest {

  def foo(f: () => String): Unit = { // 值传递;入参前计算参数中的表达式结果 ;by param
    println("foo in")
    println(f() + "ok")
  }

  def bar(f: => String): Unit = { // 名传递;方法体中执行参数表达式的结果 ;by name
    println("bar in")
    println(f + "ok")
  }

  def log(date: Date, msg: String): Unit = {
    println(s"${date.getTime} + ${msg}")
  }


  def main(args: Array[String]): Unit = {

    foo({
      println("foo run"); () => "123"
    })

    bar({
      println("bar run"); "456"
    })

    val dateToLog = log(new Date(),_:String)
    dateToLog("msg1")
    dateToLog("msg2")


  }

}
