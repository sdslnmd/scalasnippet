package base

import scala.reflect.runtime.universe

object GetObjectByString {

  def getObj(name: String): AnyVal = {

    val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)

    val module = runtimeMirror.staticModule("package.ObjectName")

    val obj = runtimeMirror.reflectModule(module)

    println(obj.instance)

  }

}
