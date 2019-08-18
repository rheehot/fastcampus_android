package fast.campus.mystudy.step2

import fast.campus.mystudy.TestClass

class ObjectTest (p : (Any) ->Unit ) : TestClass(p) {

    override fun doTest() {
        // 1. 클래스 전체를 싱글톤으로 사용할 때, 마치 java의 static class
        SingleTon.showMessage("싱글톤입니다.!")
        val obj = StaticTest()
        println(obj.msg)

        // 2. companion object
        println( StaticTest.staticVar )
        StaticTest.staticFunc()

        // 3. Android에서 자주사용됨
        var obj2 = object : fakeClickHandler(){
            override fun onClick() {
                println ("Click!!")
            }
        }
        obj2.onClick()
    }


    object SingleTon {
        var showMessage = { msg : String -> kotlin.io.println(msg) }
    }

    class StaticTest{
        var msg: String = "일반객체로 생성하면 이 변수를 액세스 가능함"
        // companion object {} 안에서 구현해야 static 가능
        companion object {
            var staticVar = "StaticTest.staticVar"
            fun staticFunc()= kotlin.io.println("StaticTest.staticFunc")
        }
    }

    // abstract 로 선언하여 반드시 구현되어야 한다!
    abstract class fakeClickHandler{
        abstract fun onClick()
    }
}
