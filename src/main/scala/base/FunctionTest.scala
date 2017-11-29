package base

object FunctionTest {

  def foo(f: () => String): Unit = { // 值传递;入参前计算参数中的表达式结果
    println("foo in")
    println(f() + "ok")
  }

  def bar(f: => String): Unit = { // 名传递;方法体中执行参数表达式的结果
    println("bar in")
    println(f + "ok")
  }

  def main(args: Array[String]): Unit = {

    foo({println("foo run");() => "123"})

    bar({println("bar run"); "456"})

  }

}
