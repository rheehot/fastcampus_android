package fast.campus.mystudy.Step3

import fast.campus.mystudy.TestClass
import java.util.*

//fun main (args : Array <String>){
class latelazyTest (p : (Any) -> Unit) : TestClass(p){
    override fun doTest() {
        var l : LateInitTest = LateInitTest()
        l.Test()

        var l2 : LazyInitTest = LazyInitTest()
        l2.Test()
    }


// 코틀린에서는 java와 달리 멤버필드를 반드시 초기화해야 한다.
// 아니면 abstract나 lateinit으로 선언해주어야 한다.

    // 1. var만 사용가능.
// 2. 늦은초기화이므로 초기화전에 사용불가
// 3. primitive 형은 불가능함.
    class LateInitTest {
        var normalValue: Int = 3
        var initTest: Int

        // "선언 = 초기화"를 안해도됨.
        lateinit var late: String

        init {
            initTest = 0
        }

        fun Test() {
            this.late = "lateinit 초기화"
            println(this.late)
        }

    }

    // 1. val로 선언
// 2. 사용하는 시점에서 초기화가 1회 발생
// 3. primitive 형도 가능
    class LazyInitTest {
        val lazy1: String by lazy {
            "lazyinit 초기화"
        }

        val lazy2: Int by lazy {
            println("1번만 실행")
            // 값
            Random().nextInt(100)
        }

        fun Test() {
            kotlin.io.println(lazy1)
            kotlin.io.println(lazy2)
            kotlin.io.println(lazy2)
        }

    }
}
