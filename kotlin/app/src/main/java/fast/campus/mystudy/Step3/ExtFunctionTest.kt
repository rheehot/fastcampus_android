package fast.campus.mystudy.Step3

import fast.campus.mystudy.TestClass

//fun main(args: Array<String>) {
class ExtFunctionTest (p : (Any) ->Unit ) : TestClass(p) {
    override fun doTest() {
        // let
        f1().let { value -> println (value) } //이름 없는 함수
        f1().let { println ( it ) } //불필요한 소가로 제외 - 람다식 (넘겨주는 변수명은 it 로 미리 정의됨)

        1232.let { it + 1 }.let { it * 3}.let { println (it) }
        var add = { a : Int, b: Int -> a + b}
        add(1, 2).let { println (it) }

        var bug : () -> Any? = { null }
        bug()?.let { println (it) } //null이 아니면 실행하고 null이면 실행 안됨

        // if (bug == null) return

        // apply()
        var obj = Test().apply { sName = "박모씨"; nAge = 49; }
        obj.aboutMe()

        // run()
        obj.run { sName = "김모씨"; aging(); sName}.let { println (it) }
        obj.aboutMe()

        // 실행결과를 리턴
        run{ 333 + 4 }.let { println (it) }

        // also
        100.let  { println(it) ; it}
            .also { println ( it + 1000) }
            .also { println (it + 20 ) }
            .let  { println (it)}


    }

    fun f1() = 10

    class Test{
        var sName : String = "무명씨"
        var nAge : Int = 49
        fun aboutMe() = kotlin.io.println("이름은 \"$sName\"이고 나이는 $nAge 입니다. ")
        fun aging() = nAge++
    }
}
